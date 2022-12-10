package com.example.keywordalarmappbackend.auth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {
  private final MemberRepository memberRepository;

  public OAuth2UserService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    String username = String.valueOf(oAuth2User.getAttributes().get(
        userRequest.getClientRegistration().getProviderDetails()
            .getUserInfoEndpoint().getUserNameAttributeName()));
    String authProvider = userRequest.getClientRegistration().getClientName();

    MemberEntity memberEntity = (!memberRepository.existsByUsername(username)) ?
        memberRepository.save(MemberEntity.create(username, authProvider))
        : memberRepository.findByUsername(username);

    return new ApplicationOAuth2User(String.valueOf(memberEntity.getId()), oAuth2User.getAttributes());
  }
}
