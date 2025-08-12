package org.abigfish.neo4j.rest;

import javax.annotation.Resource;

import org.abigfish.neo4j.entity.DongMan;
import org.abigfish.neo4j.service.DongManService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dongMan")
public class DongManController {

    @Resource
    DongManService dongManService;

    @PostMapping("/addDongMan")
    public void addDongMan(DongMan dongMan) {
    	dongManService.addDongMan(dongMan);
    }

    @PostMapping("/delById")
    public void delById(Long id) {
        dongManService.delById(id);
    }

    @PostMapping("/updateDongMane")
    public void updateDongMane(DongMan dongMan) {
        dongManService.updateById(dongMan);
    }

    @GetMapping("/getInfoById")
    public void getInfoById(Long id) {
        dongManService.getInfoById(id);
    }
    
    @GetMapping("/getAllRelationTypes")
    public void getAllRelationTypes() {
        dongManService.getAllRealationTypes();
    }

    @PostMapping("/addDMRelationShip")
    public void addDMRelationShip(String name,String relation,String to) {
        //直接指定关系
        dongManService.createRelation(name, relation, to);
    }

    @GetMapping("/getRelationsByName")
    public Result getRelationsByName(String name,String relation) {
        return Result.success("操作成功！" , dongManService.getRelationsByName(name,relation));
    }
}

