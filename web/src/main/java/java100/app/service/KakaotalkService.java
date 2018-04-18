package java100.app.service;

public interface KakaotalkService {

    <T> T me(String access_Token, Class<T> type);

}
