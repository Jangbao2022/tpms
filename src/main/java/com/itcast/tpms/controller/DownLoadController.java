package com.itcast.tpms.controller;

import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.service.curriculumSerivce.ICurriculumService;
import com.itcast.tpms.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class DownLoadController {

    @Autowired
    private ICurriculumService curriculumService;

    /**
     * 下载培养方案
     *
     * @param curriculumId
     * @return
     */
    @RequestMapping("/download")
    @ResponseBody
    public void downLoad(Long curriculumId, HttpServletResponse response) {

        CurriculumExp curriculumExp = curriculumService.getCurriculumById(curriculumId);
        String longString = curriculumExp.getMajor().getName() + "\t\t" + curriculumExp.getCurr().getName() + "\n";
        List<Course> courses = curriculumExp.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            longString += i + "\t\t";
            longString += courses.get(i).getName() + "\t\t";
            longString += (courses.get(i).getType().equals(1) ? "必修" : "选修") + "\t\t";
            longString += courses.get(i).getCredit() + "\t\t";
            longString += courses.get(i).getClassHour() + "\n";
        }

        longString += "需要必修学时\t\t" + curriculumExp.getCurr().getNeedObligatoryClassHour() + "\t\t";
        longString += "完成必修学时\t\t" + curriculumExp.getStudy().getTotalObligatoryClassHour() + "\t\t";
        longString += "未完成必修学时\t\t" + curriculumExp.getStudy().getLackObligatoryClassHour() + "\n";


        longString += "需要选修学时\t\t" + curriculumExp.getCurr().getNeedElectiveClassHour() + "\t\t";
        longString += "完成选修学时\t\t" + curriculumExp.getStudy().getLackElectiveClassHour() + "\t\t";
        longString += "未完成选修学时\t\t" + curriculumExp.getStudy().getLackElectiveClassHour() + "\n";

        longString += "需要必修学分\t\t" + curriculumExp.getCurr().getNeedObligatoryCredit() + "\t\t";
        longString += "完成必修学分\t\t" + curriculumExp.getStudy().getTotalObligatoryCredit() + "\t\t";
        longString += "未完成必修学分\t\t" + curriculumExp.getStudy().getLackObligatoryCredit() + "\n";

        longString += "需要选修学分\t\t" + curriculumExp.getCurr().getNeedElectiveCredit() + "\t\t";
        longString += "完成选修学分\t\t" + curriculumExp.getStudy().getTotalElectiveCredit() + "\t\t";
        longString += "未完成选修学分\t\t" + curriculumExp.getStudy().getLackElectiveCredit() + "\n";

        try {
            File file = new File("./target/classes/doc/" + curriculumExp.getCurr().getName() + ".txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(longString);

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = curriculumExp.getCurr().getName() + ".txt";
        String result = FileUtil.downloadFile(response, fileName);
        System.out.println(result);


    }
}
