package multi.dokgi.bookhub.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.dokgi.bookhub.user.dao.IUserDAO;

/**
 * @author Seongil, Yoon
 */
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	IUserDAO userDao;

	@Override
	public void withdraw(String userId) {
		userDao.withdraw(userId);
	}
	
	
}
