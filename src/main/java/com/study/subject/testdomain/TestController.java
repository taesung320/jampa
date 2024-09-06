package com.study.subject.testdomain;

import com.study.subject.testdomain.dto.CreateRequest;
import com.study.subject.testdomain.dto.UpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
public class TestController {
    private TestService testService;
    public TestController(TestService testService) {
        this.testService = testService;
    }


    @GetMapping("/tests")
    public HashMap<String, Object> getAllTests(
            @RequestParam(defaultValue = "slice") String pageType,
            @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "6") int size) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        if (Objects.equals(pageType, "slice")) {
            result.put("data", testService.getTestListBySlicce(page,size));

        }else{
            result.put("data", testService.getTestList(page,size));

        }
        return result;
    }

    @GetMapping("/tests/{id}")
    public HashMap<String, Object> getTest(@PathVariable(required = true) Long id) throws Exception {


        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", testService.getTest(id));
        return result;
    }

    @PostMapping("/tests")
    public HashMap<String, String> postTest(@RequestBody CreateRequest test) {
        testService.postTest(test);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    @PutMapping("/tests/{id}")
    public HashMap<String, String> putTest(@RequestBody UpdateRequest test, @PathVariable(required = true) Long id) throws Exception {
        testService.putTest(id, test);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    @DeleteMapping("/tests/{id}")
    public HashMap<String, String> deleteMember(@PathVariable(required = true) Long id) {
        testService.deleteTest(id);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }
}