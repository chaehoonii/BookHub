package multi.dokgi.bookhub.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import multi.dokgi.bookhub.user.dto.UserDTO;

/**
 * @author Seongil, Yoon
 *
 */
@Mapper
public interface IUserDAO {
	// 회원 조회
	public UserDTO findByUserId(@Param("user_id") String userId);

	public UserDTO findByMbNick(@Param("user_nick") String userNick);

	// 회원가입 1차: sns 가입
	public void savesns(UserDTO userDto);
	
	public void updateRegister(UserDTO userDto);
	
	public int withdraw(@Param("user_id") String userId);
}
