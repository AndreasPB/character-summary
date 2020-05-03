package springrestclient.controllers;

import andreas.blizzardapi.domain.Character;
import andreas.blizzardapi.domain.Realm;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Server;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.ModelAndView;
import springrestclient.services.ApiService;

@Slf4j
@Controller
public class CharacterController {

    private ApiService apiService;

    public CharacterController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index(){
        return "index";
    }


    @GetMapping(value = "/character/{realm}/{name}")
    public ModelAndView successLogin(@PathVariable String name, @PathVariable String realm, Model model)
    {
        ModelAndView mav = new ModelAndView("character_summary");

        Character character = apiService.getCharacter(realm, name);
        mav.addObject("character", character);


        return mav;
    }

    @RequestMapping(value= "/character", method = RequestMethod.POST)
    public String Login(@ModelAttribute("character") Character character)
    {
        try
        {
            return "redirect:/character/" + character.getRealmName() + "/" + character.getName();

        } catch(Exception e)
        {
            return "redirect:/";
        }
    }
}
