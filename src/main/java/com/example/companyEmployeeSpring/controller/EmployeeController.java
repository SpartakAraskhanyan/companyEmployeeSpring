package com.example.companyEmployeeSpring.controller;


import com.example.companyEmployeeSpring.entity.Company;
import com.example.companyEmployeeSpring.entity.Employee;
import com.example.companyEmployeeSpring.repasitory.CompanyRepository;
import com.example.companyEmployeeSpring.repasitory.EmployeeRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @Value("${image.path}")
    private String folderPath;


    @GetMapping("/employee")
    public String employee(ModelMap modelMap) {
        List<Employee> all = employeeRepository.findAll();
        modelMap.addAttribute("employee", all);

        return "employee";
    }

    @GetMapping("/employee/add")
    public String addEmployee(ModelMap modelMap) {
        List<Company> all = companyRepository.findAll();
        modelMap.addAttribute("companies", all);
        return "addEmployee";
    }

    @PostMapping("/employee/add")
    public String addEmployee(@ModelAttribute Employee employee, @RequestParam("profPic") MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            file.transferTo(newFile);
            employee.setProfilePic(fileName);
        }

        Optional<Company> byId = companyRepository.findById(employee.getCompany().getId());
        employeeRepository.save(employee);
        Company company = byId.get();
        company.setSize(company.getSize() + 1);
        companyRepository.save(company);
        return "redirect:/employee";
    }

    @GetMapping(value = "/employee/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    @GetMapping("/employee/delete")
    public String delete(@RequestParam("id") int id) {
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }


}
