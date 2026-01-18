@IzinIslemleri
Feature: İzin İşlemleri Modülü Yönetimi

  Background: Sisteme giriş yapılması
    Given Kullanıcı geçerli bilgilerle login olur

  @YillikIzin
  Scenario: (Pozitif Test) Yıllık İzin Seçimi
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Yıllık İzin" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "13.03.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "15.03.2026" olarak seçer
    And Kullanıcı açıklama alanına "Yıllık izin otomasyon testi Erdem" yazar
    And Kullanıcı ekle butonuna tıklar
    Then Kullanıcı "Yıllık İzin" oluşturulduğunu doğrular
    And Kullanıcı yeni oluşturduğu izni tablodan siler

  @DogumIzni
  Scenario: (Pozitif Test) Doğum İzni Seçimi
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Doğum İzni" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "13.07.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "15.07.2026" olarak seçer
    And Kullanıcı açıklama alanına "Doğum İzni otomasyon testi Erdem" yazar
    And Kullanıcı ekle butonuna tıklar
    Then Kullanıcı "Doğum İzni" oluşturulduğunu doğrular
    And Kullanıcı yeni oluşturduğu izni tablodan siler

  @UzaktanCalisma
  Scenario:(Negatif Test) Uzaktan Çalışma izninde başlangıç gününü Perşembe harici (geçersiz) bir gün seçme
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Uzaktan Çalışma" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "12.01.2026" olarak seçer
    Then Kullanıcı başlangıç tarihinin otomatik olarak ilk perşembe gününe güncellendiğini doğrular

  @SaatlikIzin
  Scenario: (Negatif Test) Günlük izin türü seçiliyken saatlik iznin kısıtlanması
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede Günlük radio butonunun seçili olduğunu doğrular
    When Kullanıcı açılan pencerede izin tipi dropdowndan "İş Arama İzni(günde 2 saat)" seçeneğini seçer
    Then Kullanıcı ekranda izin tipi saatliktir uyarısını gördüğünü doğrular

  @GunlukIzin
  Scenario: (Negatif Test) Saatlik izin türü seçiliyken günlük iznin kısıtlanması
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede Saatlik radio butonunu seçer
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Doğum Günü izni" seçeneğini seçer
    Then Kullanıcı ekranda izin tipi günlüktür uyarısını gördüğünü doğrular

  @EvlilikIzni
  Scenario: (Negatif Test) Evlilik izni süresinin 3 günden fazla seçilmesi durumunda sistemin uyarı vermesi
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Evlilik İzni" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "10.07.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "15.07.2026" olarak seçer
    Then Kullanıcı ekranda Evlilik izni en fazla 3 gün olabilir uyarısını doğrular

  @IzinBakiyesi
  Scenario: (Negatif Test) Mevcut bakiyeden fazla izin talep edilmesi durumunda sistemin uyarı vermesi
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı iznin başlama tarihini "18.01.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "18.06.2026" olarak seçer
    And Kullanıcı kalan bakiyenin eksiye düştüğünü doğrular
    And Kullanıcı ekranda izin hakedişiniz bulunmamaktadır uyarısını gördüğünü doğrular




