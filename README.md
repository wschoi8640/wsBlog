# Spring 지식 정리

### @GetMapping(url)

```java
@RequestMapping(value = url, method = RequestMethod.GET)
```

### @PostMapping(url)

```java
@RequestMapping(value = url, method = RequestMethod.POST)
```

### @Valid varType varName

* 바인딩 할 객체의 속성 규칙을 자동으로 검증하는 어노테이션
* 해당 객체에서 어노테이션을 이용해 규칙을 명시해주어야 한다.
* 유효 검사 결과는 BindingResult 을 통해 사용할 수 있다.
* <form:errors path="id" /> 태그를 사용하면 에러메시지를 표현할 수도 있다.
* 에러 메시지는 직접 정해줄 수 있다.

```java
public class User {

    @Size(min=5, max=50, message="5자에서 50자 사이의 값만 가능합니다") private String id;
    @Size(min=5, max=50) private String password;
    @Pattern(regexp="^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$") private String email;
  
}

public void getUser(User user, BindingResult result) {
    result.rejectValue("lastName", "notFound", "not found");
}
```

### @PathVariable(varName) varType varName

* @RequestParameter, @GetParameter, @PostParameter 에서 value 부분에 담겨오는 url의 일부분을 파라미터로 사용

```java
@GetMapping("/owners/{ownerId}")
public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
    Owner owner = this.owners.findById(ownerId);
    model.addAttribute(owner);
    return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
}
```

* 파라미터로 사용하고 싶은 영역을 {} 괄호로 감싸서 매핑한다.

### return "redirect:/url";

* 다른 url로 리다이렉트 처리가 됨

```java
@RequestMapping("/doA")
public String doA(RedirectAttributes rttr) {
	logger.info("doA called...");
	
	rttr.addFlashAttribute("msg", "리다이렉트시 전달할 메세지");
	return "redirect:/doB";
}
	
@RequestMapping("/doB")
public void doB(@ModelAttribute String msg) {
	logger.info("doB called... msg:"+ msg);
}
```

* RedirectAttributes를 사용하면 리다이렉트 하면서 파라미터도 전달할 수 있다.

### @ModelAttribute

* View에서 사용할 데이터를 설정하는 용도로 사용할 수 있다.
* @ModelAtrribute가 설정된 메소드는 @RequestMapping 어노테이션이 적용된 메소드보다 먼저 호출된다.
* @ModelAttribute 메소드 실행 결과로 리턴된 객체는 자동으로 Model에 저장된다.
* @ModelAttribute 메소드의 실행 결과로 리턴된 객체를 View 페이지에서 사용할 수 있다.
