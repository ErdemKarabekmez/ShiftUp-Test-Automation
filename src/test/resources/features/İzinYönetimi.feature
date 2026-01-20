@IzinIslemleri
Feature: İzin İşlemleri Modülü Yönetimi

  Background: Sisteme giriş yapılması
    Given Kullanıcı geçerli bilgilerle login olur

  @YillikIzin
  Scenario: (Pozitif Test-1) Yıllık İzin Seçimi
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Yıllık İzin" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "13.06.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "15.06.2026" olarak seçer
    And Kullanıcı açıklama alanına "Yıllık izin otomasyon testi Erdem" yazar
    And Kullanıcı ekle butonuna tıklar
    Then Kullanıcı "Yıllık İzin" oluşturulduğunu doğrular
    And Kullanıcı yeni oluşturduğu izni tablodan siler

  @DogumIzni
  Scenario: (Pozitif Test-2) Doğum İzni Seçimi
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Doğum İzni" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "28.07.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "29.07.2026" olarak seçer
    And Kullanıcı açıklama alanına "Doğum İzni otomasyon testi Erdem" yazar
    And Kullanıcı ekle butonuna tıklar
    Then Kullanıcı "Doğum İzni" oluşturulduğunu doğrular
    And Kullanıcı yeni oluşturduğu izni tablodan siler

  @TopluIzinOlusturma
  Scenario Outline: (Pozitif Test-3) Farklı izin tiplerinin başarıyla oluşturulması
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "<izinTipi>" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "<baslangic>" olarak seçer
    And Kullanıcı iznin bitiş tarihini "<bitis>" olarak seçer
    And Kullanıcı açıklama alanına "<aciklama>" yazar
    And Kullanıcı ekle butonuna tıklar
    Then Kullanıcı "<izinTipi>" oluşturulduğunu doğrular
    And Kullanıcı yeni oluşturduğu izni tablodan siler

    Examples:
      | izinTipi    | baslangic  | bitis      | aciklama                      |
      | Yıllık İzin | 13.03.2026 | 15.03.2026 | Yıllık izin otomasyon - Erdem |
      | Doğum İzni  | 13.07.2026 | 15.07.2026 | Doğum izni otomasyon - Erdem  |

  @HesaplamaKontrolu
  Scenario: (Pozitif Test-4) Tarih aralığına göre iznin toplam iş günü sayısının doğru hesaplanması
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Raporlu İzin" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "15.08.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "19.08.2026" olarak seçer
    Then Kullanıcı alt panelde toplam 5 İşgünü kullanıldığını doğrular

  @IseBaslamaTarihiKontrolu
  Scenario: (Pozitif Test-5) İzin Bitimi İşe Başlama Tarihi Kontrolü
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Home Office" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "22.09.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "25.09.2026" olarak seçer
    Then Kullanıcı işe başlama tarihinin "26.09.2026" olarak güncellendiğini doğrular

  @UzaktanCalisma @ParalelTest
  Scenario:(Negatif Test-1) Uzaktan Çalışma izninde başlangıç gününü Perşembe harici (geçersiz) bir gün seçme
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Uzaktan Çalışma" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "12.01.2026" olarak seçer
    Then Kullanıcı iznin başlama tarihinin ilk geçerli gün olan "15.01.2026" olarak güncellendiğini doğrular

  @DogumGunuIzni @ParalelTest
  Scenario:(Negatif Test-2) Doğum Günü izninde başlangıç gününü Pazartesi harici (geçersiz) bir gün seçme
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Doğum Günü izni" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "21.02.2026" olarak seçer
    Then Kullanıcı iznin başlama tarihinin ilk geçerli gün olan "23.02.2026" olarak güncellendiğini doğrular


  @SaatlikIzin @ParalelTest
  Scenario: (Negatif Test-3) Günlük izin türü seçiliyken saatlik iznin kısıtlanması
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede Günlük radio butonunun seçili olduğunu doğrular
    When Kullanıcı açılan pencerede izin tipi dropdowndan "İş Arama İzni(günde 2 saat)" seçeneğini seçer
    Then Kullanıcı ekranda izin tipi saatliktir uyarısını gördüğünü doğrular
    Then Kullanıcı Ekle butonunun disabled olduğunu doğrular

  @GunlukIzin
  Scenario: (Negatif Test-4) Saatlik izin türü seçiliyken günlük iznin kısıtlanması
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede Saatlik radio butonunu seçer
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Doğum Günü izni" seçeneğini seçer
    Then Kullanıcı ekranda izin tipi günlüktür uyarısını gördüğünü doğrular
    Then Kullanıcı Ekle butonunun disabled olduğunu doğrular

  @EvlilikIzni
  Scenario: (Negatif Test-5) Evlilik izni süresinin 3 günden fazla seçilmesi durumunda sistemin uyarı vermesi
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Evlilik İzni" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "10.07.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "15.07.2026" olarak seçer
    Then Kullanıcı ekranda Evlilik izni en fazla üç gün olabilir uyarısını doğrular
    Then Kullanıcı Ekle butonunun disabled olduğunu doğrular

  @IzinTekrarı
  Scenario: (Negatif Test-6) Aynı izni aynı tarihte tekrar seçme
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Yıllık İzin" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "03.04.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "06.04.2026" olarak seçer
    And Kullanıcı açıklama alanına "Ücretsiz izin otomasyon testi Erdem" yazar
    And Kullanıcı ekle butonuna tıklar
    Then Kullanıcı "Yıllık İzin" oluşturulduğunu doğrular
    And Kullanıcı aynı tarihlerde yeni izin eklemek için tekrar izin ekle ekranına gelir
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Yıllık İzin" seçeneğini seçer
    And Kullanıcı iznin başlama tarihini "03.04.2026" olarak seçer
    Then Kullanıcı ekranda izin tanımlı uyarısını gördüğünü doğrular
    Then Kullanıcı Ekle butonunun disabled olduğunu doğrular



  @IzinBakiyesi
  Scenario: (Negatif Test-7 - BUG ) Mevcut bakiyeden fazla izin talep edilmesi durumunda sistemin uyarı vermesi
        # Mevcut bakiyeyi aşan izin taleplerinde sistem, "İzin hak edişiniz bulunmamaktadır" uyarısını göstermesine rağmen,
        # "EKLE" butonunu disabled hale getirmemekte ve izin talebinin kaydedilmesine izin vermektedir.
        # Bu durumda "EKLE" butonu disabled olmalıdır.
        # BU BİR BUG TIR
    Given Kullanıcı izin ekle menüsüne gider
    When Kullanıcı açılan pencerede izin tipi dropdowndan "Yıllık İzin" seçeneğini seçer
    When Kullanıcı iznin başlama tarihini "11.02.2026" olarak seçer
    And Kullanıcı iznin bitiş tarihini "18.09.2026" olarak seçer
    And Kullanıcı kalan bakiyenin eksiye düştüğünü doğrular
    And Kullanıcı ekranda izin hakedişiniz bulunmamaktadır uyarısını gördüğünü doğrular
    And Kullanıcı ekle butonuna tıklar
    Then Kullanıcı "Yıllık İzin" oluşturulduğunu doğrular
    And Kullanıcı yeni oluşturduğu izni tablodan siler


  @GecmisTarihliIzin
  Scenario: (Negatif Test-8 - BUG ) Geçmiş tarihli izin talebinin sistem tarafından engellenmesi
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







