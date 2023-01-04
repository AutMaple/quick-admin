package com.autmaple.oauth.controller;

import com.autmaple.oauth.entity.Menu;
import com.autmaple.oauth.model.MenuNode;
import com.autmaple.oauth.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author AutMaple
 * @since 2023-01-04
 */
@RestController
@RequestMapping("/oauth/menu")
@RequiredArgsConstructor
public class MenuController {

}
