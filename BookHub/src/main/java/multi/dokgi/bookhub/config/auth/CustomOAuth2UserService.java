package multi.dokgi.bookhub.config.auth;

import lombok.RequiredArgsConstructor;
import multi.dokgi.bookhub.config.auth.dto.OAuthAttributes;
import multi.dokgi.bookhub.config.auth.dto.SessionUser;
import multi.dokgi.bookhub.user.dao.IUserDAO;
import multi.dokgi.bookhub.user.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;


/**
 * @author Seongil, Yoon
 * SNS로그인 이후 가져온 사용자의 정보(email,name)등을 기반으로 가입 및 정보수정, 세션저장등의 기능을 수행 
 */
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    
    @Autowired
    IUserDAO userDao;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행 중인 서비스를 구분하는 코드 // google 
        String userProvider = userRequest
                .getClientRegistration()
                .getRegistrationId();
        // oauth2 로그인 진행 시 키가 되는 필드값 (고객 식별자 필드값) 
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        
        String userId = userProvider + "_" + userNameAttributeName;

        // OAuthAttributes: attribute를 담을 클래스 (개발자가 생성)
        OAuthAttributes attributes = OAuthAttributes
                .of(userProvider, userNameAttributeName, oAuth2User.getAttributes()); // 로그인, 로그인한 유저 정보 받아오기

        UserDTO user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }


    // 유저가 최초 로그인인지, 기존 유저인지 확인
    private UserDTO saveOrUpdate(OAuthAttributes attributes) {
    	
    	UserDTO userDto;
        
        if(userDao.findByUserId(attributes.getUserId()) != null){
            System.out.println("이미 가입되어 있는 회원입니다.");
            userDto = userDao.findByUserId(attributes.getUserId());
        }
        
        // 최초 로그인한 유저면 자동 가입 실행
        else {
        	userDto = attributes.toEntity();
        	userDto.setUserNick(userDto.getUserNick() +  + (int)(Math.random() * 10000 + 1)); // 닉네임에 랜덤값 추가
        	userDao.savesns(userDto);
            
            System.out.println("최초 로그인으로 자동 가입됩니다.");
            userDto = userDao.findByUserId(attributes.getUserId());
        }

        return userDto;
    }
}

