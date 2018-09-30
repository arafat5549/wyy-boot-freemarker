package com.jqmkj.java.modules.sys.web;

import com.jqmkj.java.common.config.JqmkjProperties;
import com.jqmkj.java.util.spring.DefaultProfileUtil;
import com.jqmkj.java.web.rest.vm.ProfileInfoVM;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wyy
 */
@RestController
@RequestMapping("${jqmkj.adminPath}")
public class ProfileInfoResource {

    private Environment environment;

    private JqmkjProperties jqmkjProperties;

    public ProfileInfoResource(Environment environment, JqmkjProperties jqmkjProperties) {
        this.environment = environment;
        this.jqmkjProperties = jqmkjProperties;
    }

    @GetMapping(value = "/profile-info",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProfileInfoVM getActiveProfiles() {
        return new ProfileInfoVM(DefaultProfileUtil.getActiveProfiles(environment), getRibbonEnv());
    }

    private String getRibbonEnv() {
        String[] activeProfiles = DefaultProfileUtil.getActiveProfiles(environment);
        String[] displayOnActiveProfiles = jqmkjProperties.getRibbon().getDisplayOnActiveProfiles();

        if (displayOnActiveProfiles == null) {
            return null;
        }

        List<String> ribbonProfiles = new ArrayList<>(Arrays.asList(displayOnActiveProfiles));
        List<String> springBootProfiles = Arrays.asList(activeProfiles);
        ribbonProfiles.retainAll(springBootProfiles);

        if (ribbonProfiles.size() > 0) {
            return ribbonProfiles.get(0);
        }
        return null;
    }


}
