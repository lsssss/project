package java100.app.web.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java100.app.domain.Member;
import java100.app.domain.Trainning;
import java100.app.domain.TrainningUploadFile;
import java100.app.service.MemberService;
import java100.app.service.TrainningService;

@RestController
@RequestMapping("/trainning")
@SessionAttributes("loginUser")
public class TrainningController {

    @Autowired
    ServletContext servletContext;
    @Autowired
    TrainningService trainningService;
    @Autowired
    MemberService memberService;

    @RequestMapping("list")
    public Object list(@RequestParam(value = "pn", defaultValue = "1") int pageNo,
            @RequestParam(value = "ps", defaultValue = "5") int pageSize,
            @RequestParam(value = "words", required = false) String[] words,
            @RequestParam(value = "oc", required = false) String orderColumn,
            @RequestParam(value = "al", required = false) String align) throws Exception {

        // UI 제어와 관련된 코드는 이렇게 페이지 컨트롤러에 두어야 한다.
        //
        if (pageNo < 1) {
            pageNo = 1;
        }

        if (pageSize < 6 || pageSize > 15) {
            pageSize = 6;
        }

        HashMap<String, Object> options = new HashMap<>();
        if (words != null && words[0].length() > 0) {
            options.put("words", words);
        }
        options.put("orderColumn", orderColumn);
        options.put("align", align);

        int totalCount = trainningService.getTotalCount();
        int lastPageNo = totalCount / pageSize;
        if ((totalCount % pageSize) > 0) {
            lastPageNo++;
        }
        HashMap<String, Object> result = new HashMap<>();
        // view 컴포넌트가 사용할 값을 Model에 담는다.
        result.put("pageNo", pageNo);
        result.put("lastPageNo", lastPageNo);
        result.put("list", trainningService.list(pageNo, pageSize, options));

        return result;
    }

    @RequestMapping("add")
    public Object add(Trainning trainning, @ModelAttribute(value = "loginUser") Member loginUser, MultipartFile[] file)
            throws Exception {

        String uploadDir = servletContext.getRealPath("/download");

        ArrayList<TrainningUploadFile> uploadFiles = new ArrayList<>();

        for (MultipartFile part : file) {
            if (part.isEmpty())
                continue;

            String filename = this.writeUploadFile(part, uploadDir);

            uploadFiles.add(new TrainningUploadFile(filename));
        }

        trainning.setFiles(uploadFiles);
        trainning.setMember(loginUser);
        trainningService.add(trainning);
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("data", memberService.get(trainning.getMember().getMemberNo()));
        return result;

    }

    @RequestMapping("{no}")
    public Object view(@PathVariable int no) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", trainningService.get(no));
        return result;
    }

    @RequestMapping("update")
    public Object update(Trainning trainning, MultipartFile[] file) throws Exception {

        String uploadDir = servletContext.getRealPath("/download");

        ArrayList<TrainningUploadFile> uploadFiles = new ArrayList<>();

        for (MultipartFile part : file) {
            if (part.isEmpty())
                continue;

            String filename = this.writeUploadFile(part, uploadDir);

            uploadFiles.add(new TrainningUploadFile(filename));
        }

        trainning.setFiles(uploadFiles);
        trainningService.update(trainning);
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "success");

        return result;
    }

    @RequestMapping("like")
    public Object like(Trainning trainning) throws Exception {

        int likeResult = trainningService.like(trainning);
        HashMap<String, Object> result = new HashMap<>();

        if (likeResult != 0)
            result.put("status", "success");
        else
            result.put("status", "fail");

        return result;
    }

    @RequestMapping("dislike")
    public Object dislike(Trainning trainning) throws Exception {

        int likeResult = trainningService.dislike(trainning);
        HashMap<String, Object> result = new HashMap<>();

        if (likeResult != 0)
            result.put("status", "success");
        else
            result.put("status", "fail");

        return result;
    }

    @RequestMapping("checkLike")
    public Object checkLike(Trainning trainning) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        System.out.println("ttttttttttttttt: " +trainningService.checkLike(trainning));
        if (trainning.getLiked() == 0)
            return result.put("status", "unchecked");
        else
            return result.put("status", "checked");

    }

    @RequestMapping("countLike")
    public Object countLike() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        result.put("count", trainningService.countLikes());
        result.put("status", "success");

        return result;
    }

    @RequestMapping("delete")
    public Object delete(int no) throws Exception {

        trainningService.delete(no);

        HashMap<String, Object> result = new HashMap<>();
        result.put("status", "success");

        return result;
    }

    long prevMillis = 0;
    int count = 0;

    // 다른 클라이언트가 보낸 파일명과 중복되지 않도록
    // 서버에 파일을 저장할 때는 새 파일명을 만든다.
    synchronized private String getNewFilename(String filename) {
        long currMillis = System.currentTimeMillis();
        if (prevMillis != currMillis) {
            count = 0;
            prevMillis = currMillis;
        }

        return currMillis + "_" + count++ + extractFileExtName(filename);
    }

    // 파일명에서 뒤의 확장자명을 추출한다.
    private String extractFileExtName(String filename) {
        int dotPosition = filename.lastIndexOf(".");

        if (dotPosition == -1)
            return "";

        return filename.substring(dotPosition);
    }

    private String writeUploadFile(MultipartFile part, String path) throws IOException {

        String filename = getNewFilename(part.getOriginalFilename());
        part.transferTo(new File(path + "/" + filename));
        return filename;
    }
}
