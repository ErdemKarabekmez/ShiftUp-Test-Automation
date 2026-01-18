Feature: Sisteme Giriş

  @login
  Scenario: Geçerli bilgilerle sisteme giriş yapma
    Given Kullanıcı login sayfasına gider
    When Kullanıcı kullanıcı adını girer
    And Kullanıcı Şifreyi girer
    And Kullanıcı Giriş butonuna tıklar
    Then Kullanıcı sisteme Login olduğunu doğrular