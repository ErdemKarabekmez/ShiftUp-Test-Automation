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

  @HesaplamaKontrolu
  Scenario: (Pozitif Test) Tarih aralığına göre iznin toplam iş günü sayısının doğru hesaplanması
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Raporlu İzin" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "14.07.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "19.07.2026" olarak seçer
    Then Kullanıcı alt panelde toplam 6 İşgünü kullanıldığını doğrular


  @UzaktanCalisma
  Scenario:(Negatif Test) Uzaktan Çalışma izninde başlangıç gününü Perşembe harici (geçersiz) bir gün seçme
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Uzaktan Çalışma" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "12.01.2026" olarak seçer
    Then Kullanıcı başlangıç tarihinin otomatik olarak ilk perşembe gününe güncellendiğini doğrular

  @DogumGunuIzni
  Scenario:(Negatif Test) Doğum Günü izninde başlangıç gününü Pazartesi harici (geçersiz) bir gün seçme
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Doğum Günü izni" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "21.02.2026" olarak seçer
    Then Kullanıcı başlangıç tarihinin otomatik olarak ilk pazartesi gününe güncellendiğini doğrular

  @SaatlikIzin
  Scenario: (Negatif Test) Günlük izin türü seçiliyken saatlik iznin kısıtlanması
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede Günlük radio butonunun seçili olduğunu doğrular
    When Kullanıcı açılan pencerede izin tipi dropdowndan "İş Arama İzni(günde 2 saat)" seçeneğini seçer
    Then Kullanıcı ekranda izin tipi saatliktir uyarısını gördüğünü doğrular
    Then Kullanıcı Ekle butonunun disabled olduğunu doğrular

  @GunlukIzin
  Scenario: (Negatif Test) Saatlik izin türü seçiliyken günlük iznin kısıtlanması
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede Saatlik radio butonunu seçer
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Doğum Günü izni" seçeneğini seçer
    Then Kullanıcı ekranda izin tipi günlüktür uyarısını gördüğünü doğrular
    Then Kullanıcı Ekle butonunun disabled olduğunu doğrular

  @EvlilikIzni
  Scenario: (Negatif Test) Evlilik izni süresinin 3 günden fazla seçilmesi durumunda sistemin uyarı vermesi
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Evlilik İzni" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "10.07.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "15.07.2026" olarak seçer
    Then Kullanıcı ekranda Evlilik izni en fazla 3 gün olabilir uyarısını doğrular
    Then Kullanıcı Ekle butonunun disabled olduğunu doğrular

  @IzinBakiyesi
  Scenario: (Negatif Test - BUG ) Mevcut bakiyeden fazla izin talep edilmesi durumunda sistemin uyarı vermesi
        # Mevcut bakiyeyi aşan izin taleplerinde sistem, "İzin hak edişiniz bulunmamaktadır" uyarısını göstermesine rağmen,
        # "EKLE" butonunu disabled hale getirmemekte ve izin talebinin kaydedilmesine izin vermektedir.
        # Bu durumda "EKLE" butonu disabled olmalıdır.
        # BU BİR BUG TIR
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Yıllık İzin" seçeneğini seçer
    When Kullanıcı iznin başlama tarihini "18.01.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "18.06.2026" olarak seçer
    And Kullanıcı kalan bakiyenin eksiye düştüğünü doğrular
    And Kullanıcı ekranda izin hakedişiniz bulunmamaktadır uyarısını gördüğünü doğrular
    And Kullanıcı ekle butonuna tıklar
    Then Kullanıcı "Yıllık İzin" oluşturulduğunu doğrular
    And Kullanıcı yeni oluşturduğu izni tablodan siler


  @GecmisTarihliIzin
  Scenario: (Negatif Test - BUG ) Geçmiş tarihli izin talebinin sistem tarafından engellenmesi
        # Geçmiş tarihli izin taleplerinde sistem, izin talebinin kaydedilmesine izin vermektedir.
        # Bu gibi durumlarda izin talebi reddedilmelidir.
        # BU BİR BUG TIR
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Yıllık İzin" seçeneğini seçer
    When Kullanıcı iznin başlama tarihini "02.07.2025" olarak seçer
    And Kullanıcı iznin bitiş tarihini "08.07.2025" olarak seçer
    And Kullanıcı açıklama alanına "Geçmiş Tarihli izin testi Erdem" yazar
    And Kullanıcı ekle butonuna tıklar
    Then Kullanıcı "Yıllık İzin" oluşturulduğunu doğrular
    And Kullanıcı yeni oluşturduğu izni tablodan siler





