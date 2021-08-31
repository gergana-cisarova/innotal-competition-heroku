package com.example.competition.web;


import com.example.competition.models.binding.EvaluationAddBindingModel;
import com.example.competition.models.binding.StudentAddBindingModel;
import com.example.competition.models.entity.Evaluation;
import com.example.competition.models.entity.UserEntity;
import com.example.competition.models.entity.enums.Iteration;
import com.example.competition.models.service.EvaluationServiceModel;
import com.example.competition.models.service.StudentServiceModel;
import com.example.competition.models.service.UserRegistrationServiceModel;
import com.example.competition.models.view.EvaluationViewModel;
import com.example.competition.models.view.StudentViewModel;
import com.example.competition.services.CarouselService;
import com.example.competition.services.EvaluationService;
import com.example.competition.services.StudentService;
import com.example.competition.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final CarouselService carouselService;
    private final ModelMapper modelMapper;
    private final StudentService studentService;
    private final EvaluationService evaluationService;
    private final UserService userService;

    public StudentController(CarouselService carouselService, ModelMapper modelMapper, StudentService studentService, EvaluationService evaluationService, UserService userService) {
        this.carouselService = carouselService;
        this.modelMapper = modelMapper;
        this.studentService = studentService;
        this.evaluationService = evaluationService;
        this.userService = userService;
    }

    @ModelAttribute("studentAddBindingModel")
    public StudentAddBindingModel createBindingModel() {
        return new StudentAddBindingModel();
    }

    @ModelAttribute("exists")
    public int evaluationExists() {
        return 0;
    }
    @ModelAttribute("existingEval")
    public EvaluationViewModel existingEvaluation() {
        return new EvaluationViewModel();
    }

    @ModelAttribute("evaluationAddBindingModel")
    public EvaluationAddBindingModel projectAddBindingModel() {
        return new EvaluationAddBindingModel();
    }

    @ModelAttribute("message")
    public String message() {
        return "";
    }

    @GetMapping("/results")
    public String showResultsReg(Model model) {
        return "results";
    }

    @GetMapping("/results_finals")
    public String showResultsFinals(Model model) {
        return "results_finals";
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("students", studentService.getAll(Iteration.Regular));

        return "students-all";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {

        model.addAttribute("firstImg", carouselService.firstImage());
        model.addAttribute("secondImg", carouselService.secondImage());
        model.addAttribute("thirdImg", carouselService.thirdImage());

        return "student-add";
    }

    @GetMapping("/allFinalists")
    public String showAllFinalists(Model model) {
        model.addAttribute("students", studentService.getAll(Iteration.Finalist));

        return "students-all";
    }

    @PostMapping("/add")
    public String addStudentPost(@Valid StudentAddBindingModel studentAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("studentAddBindingModel", studentAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentAddBindingModel", bindingResult);
            return "redirect:/students/add";
        }

        StudentServiceModel studentServiceModel = modelMapper.map(
                studentAddBindingModel,
                StudentServiceModel.class);

        studentService.createStudent(studentServiceModel);
        redirectAttributes.addFlashAttribute("message", "The student was added");

        return "redirect:/students/all";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id,
                              Model model) {

        StudentViewModel studentViewModel = studentService.getStudentView(id);
        model.addAttribute("current", studentViewModel);

        return "student-details";
    }

    @GetMapping("/evaluate_all")
    public String evaluateAll(Model model) {
        model.addAttribute("students", studentService.getAll(Iteration.Regular));

        return "evaluate-all";
    }

    @GetMapping("/evaluate_finals")
    public String evaluateFinalists(Model model) {
        model.addAttribute("students", studentService.getAll(Iteration.Finalist));

        return "evaluate-finals";
    }

    @GetMapping("/evaluate/{id}")
    public String evaluateStudent(@PathVariable String id, Model model,@AuthenticationPrincipal UserDetails principal) {
        StudentServiceModel studentServiceModel = studentService.extractStudentModel(id);
        model.addAttribute("studentServiceModel", studentServiceModel);

        UserEntity evaluator = userService.findByUsername(principal.getUsername());
        List<EvaluationViewModel> existing = evaluationService.findEvaluationByEvaluator(evaluator.getId(), id);
        if (existing.size()==1) {
            model.addAttribute("exists", 1);
            model.addAttribute("existingEval", existing.get(0));
        }
        return "evaluation-add";
    }

    @PostMapping("/evaluate/{id}")
    public String evaluateStudentPost(@PathVariable String id,
                                      @Valid EvaluationAddBindingModel evaluationAddBindingModel,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes,
                                      Model model,
                                      @AuthenticationPrincipal UserDetails principal) {
        model.addAttribute("id", id);
        model.addAttribute("evaluator", principal);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("evaluationAddBindingModel", evaluationAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.evaluationAddBindingModel", bindingResult);
            return "redirect:/students/evaluate/{id}";
        }

        EvaluationServiceModel evaluationServiceModel = modelMapper.map(
                evaluationAddBindingModel, EvaluationServiceModel.class);
        UserEntity evaluator = userService.findByUsername(principal.getUsername());
        evaluationServiceModel.setEvaluated(studentService.extractStudentModel(id));
        UserRegistrationServiceModel userModel = modelMapper.map(evaluator, UserRegistrationServiceModel.class);
        evaluationServiceModel.setEvaluator(userModel);
        evaluationService.createEvaluation(evaluationServiceModel);

        redirectAttributes.addFlashAttribute("message", "You evaluated this team");

        return "redirect:/students/evaluate_all";
    }


    @GetMapping("/evaluateFinals/{id}")
    public String evaluateFinalistStudent(@PathVariable String id, Model model,@AuthenticationPrincipal UserDetails principal) {
        StudentServiceModel studentServiceModel = studentService.extractStudentModel(id);
        model.addAttribute("studentServiceModel", studentServiceModel);
        UserEntity evaluator = userService.findByUsername(principal.getUsername());
        List<EvaluationViewModel> existing = evaluationService.findEvaluationByEvaluator(evaluator.getId(), id);
        if (existing.size()==1) {
            model.addAttribute("exists", 1);
            model.addAttribute("existingEval", existing.get(0));
        }
        return "evaluationFinals-add";
    }

    @PostMapping("/evaluateFinals/{id}")
    public String evaluateFinalistStudentPost(@PathVariable String id,
                                              @Valid EvaluationAddBindingModel evaluationAddBindingModel,
                                              BindingResult bindingResult,
                                              RedirectAttributes redirectAttributes,
                                              Model model,
                                              @AuthenticationPrincipal UserDetails principal) {
        model.addAttribute("id", id);
        model.addAttribute("evaluator", principal);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("evaluationAddBindingModel", evaluationAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.evaluationAddBindingModel", bindingResult);
            return "redirect:/students/evaluateFinals/{id}";
        }

        EvaluationServiceModel evaluationServiceModel = modelMapper.map(
                evaluationAddBindingModel, EvaluationServiceModel.class);

        evaluationServiceModel.setEvaluated(studentService.extractStudentModel(id));
        UserEntity evaluator = userService.findByUsername(principal.getUsername());
        UserRegistrationServiceModel userModel = modelMapper.map(evaluator, UserRegistrationServiceModel.class);
        evaluationServiceModel.setEvaluator(userModel);
        evaluationService.createEvaluation(evaluationServiceModel);

        redirectAttributes.addFlashAttribute("message", "You evaluated this team");

        return "redirect:/students/evaluate_finals";

    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id,
                                RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("message", "You deleted the team");

        return "redirect:/students/all";
    }

    @GetMapping("/guidelines")
    public String guidelines() {
        return "guidelines";
    }
}
