從頭學Spring系列之一
===

## 前情提要
> 最近協助一位正在讀大學的年輕朋友，安排一系列的程式語言學習，其中Java與Spring是我想到最適合入手物件導向程式語言的標的，因此，我打算把手上幾本與Java、Spring、設計模式等書籍，做一系列的整理，並以Spring6與測試的角度去思考與觀察應用程式的開發迭代。
> Spring Framework自2003年被發展出來，即是為了解決在企業級應用開發時，所遇到的各種複雜問題。其記住的兩大主軸為IoC與AOP，我將會在後續的文章中，一一以實際程式碼進行說明。這篇我先為大家示範Spring的Bean組裝方式。

## 主題
> 在Java應用程式開發時，物件的生成與管理是極為重要的一件事情，尤其在系統需要面對不斷改變的要求，除了許多的設計模式，Spring框架也提供了這樣的能力。這篇將以紅頭鴨(Red Head Duck)與它可以出的聲音Quack做為範例，並利用Spring的Configuration、Bean、Autowire將叫聲注入至紅頭鴨之中。
### 設定
> Maven部分：為了不讓學習目標偏移，我們讓設定只使用Spring Context，避免使用太過於複雜的Spring Boot 設定。
```gherkin=
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>6.0.12</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>RELEASE</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>6.0.12</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
> 程式碼部分:設定的類別DuckConfig裡，我們使用了@Configuration，並在method quack與redheadDuck上一行，使用了@Bean，表示將這兩個Bean交給Spring的Container去管理，後許也可以讓Container裡的服務取用，測試程式裡使用的@Autowire就是取用這邊指定的Bean。
```gherkin=
@Configuration
public class DuckConfig {

    @Bean
    public QuackBehavior quack(){
        return new Quack();
    }
    @Bean
    public Duck redheadDuck(){
        Duck redheadDuck = new RedheadDuck();
        redheadDuck.setQuackBehavior(quack());
        return redheadDuck;
    }
}
```
### 測試
> 許多Spring的書籍，或是許多人的示範程式碼，多半是使用main method或是用網頁的方式做為驗證結果的方式，但實務上，不太會在正式程式碼中放入 main method，用網頁去驗證結果的效率不高，且容易讓初學者失焦。因此，我使用Java中最常被使用的開發框架JUnit，這在後續的開發工作中引入單元測試，甚至是測試驅動開發(Test Driven Development aka TDD)都會是一個比較好的開始。  

> 在Spring框架與其他測試框架有不錯的整合，在這裡我們先以JUnit5做為主要的測試框架；因此過去的@RunWith需改成@ExtendWith，而SpringJUnit4ClassRunner.class須改用SpringExtension.class。透過這個整合功能，我們可以在不用整個應用啟動的情況下，針對部分功能進行執行與測試。
> 在method beans_should_not_be_null_test，我們想確認用@Autowire去Spring Container裡，可以取用到紅頭鴨與叫聲的Bean。
> method duck_display_test 是要確認鴨子的顯示名稱。
> method redheads_sound_quack_test 則是要確認它的叫聲，是否為Quack。
```gherkin=
@ExtendWith(SpringExtension.class) // JUnit4與Spring5為 @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= DuckConfig.class)
public class DuckConfigTests {

    @Autowired
    Duck redheadDuck;

    @Autowired
    QuackBehavior quack;
    
    @Test
    public void beans_should_not_be_null_test(){
        assertNotNull(redheadDuck);
        assertNotNull(quack);
    }

    @Test
    public void duck_display_test(){
        assertEquals("RedheadDuck", redheadDuck.display());
    }

    @Test
    public void redheads_sound_quack_test(){
        assertEquals("Quack", redheadDuck.performQuack());
    }
}
```

