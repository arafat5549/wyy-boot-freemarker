package com.jqmkj.java.vo.account;


import com.jqmkj.java.util.domain.Globals;
import com.jqmkj.java.vo.sys.UserVo;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * View Model object for storing a user's credentials.
 */
@Data
@ToString
public class PasswordChangeVo {

    @NotBlank
    private String oldPassword;

    @NotBlank
    @Size(min = 6, max = UserVo.PASSWORD_MAX_LENGTH)
    private String newPassword;

    @NotBlank
    @Size(min = 6, max = UserVo.PASSWORD_MAX_LENGTH)
    private String confirmPassword;

}
