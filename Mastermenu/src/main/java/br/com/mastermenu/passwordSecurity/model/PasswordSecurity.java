package br.com.mastermenu.passwordSecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.mastermenu.user.model.User;
import br.com.mastermenu.util.HashUtil;

@Entity(name = "passwordSecurity")
public class PasswordSecurity {
	@Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user = new User();

    private String SALT;

    public PasswordSecurity() {
        this.SALT = HashUtil.generateSALT();
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	    public String getSALT() {
        return SALT;
    }
}
