# 🚀 ShiftUp Test Otomasyon Framework
Bu proje, ShiftUp uygulamasını test etmek için geliştirilmiş, **BDD (Behavior Driven Development)** yaklaşımını benimseyen modern bir test otomasyon frameworkudur..

## 🛠 Kullanılan Teknolojiler

* **Java 17** (Programlama Dili)
* **Selenium 4** (Web Otomasyonu)
* **Cucumber** (BDD - Gherkin Syntax)
* **Maven** (Proje ve Kütüphane Yönetimi)
* **TestNG** (Test Yönetimi ve Paralel Koşum)
* **Allure Report** (Detaylı Görsel Raporlama)
* **Page Object Model (POM)** (Tasarım Deseni)

## 📂 Proje Yapısı
```text
ShiftUp (Project Root)
├── pom.xml                  # Proje bağımlılıkları ve Build ayarları (Maven)
├── configuration.properties # Global ayarlar (Browser, Url, Environment vb.)
├── README.md                # Proje dokümantasyonu
└── src
    └── test
        ├── java/com/shiftup
        │   ├── pages              # Web elementleri (@FindBy) ve sayfa metotları
        │   ├── runners            # Testleri çalıştıran sınıflar (TestRunner)
        │   ├── step_definitions   # Cucumber adımlarının Java kod karşılıkları
        │   └── utilities          # Yardımcı araçlar (Driver, ConfigReader vb.)
        └── resources
            ├── features           # Test senaryoları (.feature dosyaları)
            └── allure.properties  # Raporlama ayarları
```
## 📋 Ön Koşullar 
Bu projeyi sorunsuz çalıştırabilmek için bilgisayarınızda aşağıdaki araçların yüklü olması gerekmektedir:

* **Java JDK 17** veya üzeri (Proje Java 17 ile derlenmektedir).
* **Maven** (Bağımlılık yönetimi için).
* **IntelliJ IDEA** (Önerilen IDE).
* **Chrome Browser** (Testler varsayılan olarak Chrome üzerinde koşmaktadır).

---
## ⚙️ Kurulum (Setup)

Projeyi yerel makinenize kurmak ve bağımlılıkları yüklemek için aşağıdaki adımları takip edin:

**1. Projeyi Klonlayın**
Terminalinizi açın ve aşağıdaki komutu girerek projeyi bilgisayarınıza indirin:
```bash
git clone https://github.com/ErdemKarabekmez/ShiftUp-Test-Automation.git
```
**2. Projeyi IDE ile Açın**
IntelliJ IDEA'yı açın ve Open seçeneği ile projenin ana klasörünü (pom.xml dosyasının olduğu klasör) seçin.

**3. Bağımlılıkları Yükleyin**
Proje açıldıktan sonra kütüphaneleri indirmek için terminalde şu komutu çalıştırın:
```bash
mvn clean install
```
**Alternatif:** IntelliJ ekranının sağ tarafındaki Maven sekmesine tıklayıp sol üstteki "Reload All Maven Projects" butonuna basabilirsiniz.

**4. Ayarları Kontrol Edin** configuration.properties dosyasını açarak tarayıcı ve URL ayarlarının doğruluğunu teyit edebilirsiniz.

## 🌐 Tarayıcı Ayarları ve Headless Mod

Testlerin hangi tarayıcıda koşacağını `configuration.properties` dosyasından belirleyebilirsiniz.

**Ayarı Değiştirmek İçin:**
1. `configuration.properties` dosyasını açın.
2. `browser` anahtarının karşısına aşağıdaki değerlerden birini yazın:

* `chrome`: Testleri standart **Google Chrome** penceresinde görsel olarak çalıştırır. (Varsayılan)
* `headless-chrome`: Testleri **arayüzsüz (GUI olmadan)** arka planda çalıştırır. Tarayıcı penceresi açılmaz, bu sayede testler daha az kaynak tüketir ve daha hızlı tamamlanır.
* `firefox`: Testleri **Mozilla Firefox** tarayıcısında çalıştırır.

## ▶️ Testleri Çalıştırma ve Raporlama

Kurulum bittikten sonra testleri çalıştırmak ve raporları görmek için şu 3 yöntemden birini seçebilirsiniz:

### 1. Terminal ile (Allure Raporu Üretir)
Tüm testleri koşmak ve Allure Raporu üretmek istiyorsanız Terminale önce şu komutu yazıp Enter'a basın:(Bu komut testleri çalıştıracaktır.)
```bash
mvn clean test
```
Testler çalıştıktan sonra yine Terminale şu komutu yazıp Enter'a basın:
```bash
mvn allure:serve
```
Allure Raporu tarayıcınızda açılacaktır.

### 2. Maven Paneli ile (Allure Raporu Üretir)
Kod yazmadan çalıştırmak için sağ taraftaki **Maven** menüsünü kullanın:
1. `Lifecycle` klasörünü açın.
2. Önce `clean` seçeneğine çift tıklayın (Eski raporları temizler).
3. Ardından `test` seçeneğine çift tıklayın. (Testlerinizi çalıştırır).

Testleriniz çalıştıktan sonra

1. `Maven` menüsünü açın. (Sağ tarafta)
2. `Plugins` klasörünü açın.
3. `allure` klasörünü açın.
4. `allure:serve` seçeneğine çift tıklayın.
   Allure Raporu tarayıcınızda açılacaktır.

### 3. Runner Class ile (Hızlı Kontrol-Basit Rapor Üretir)
Geliştirme yaparken hızlıca sonuç görmek için:
1. src/test/java/com/shiftup/runners/TestRunner.java dosyasını açın.
2. Sınıf isminin yanındaki yeşil **Run (Play)** butonuna basın.

> **Not:** Bu yöntemle çalıştırdığınızda Allure Raporu yerine basit bir HTML raporu oluşur. 
 Bu raporu görmek için `target` klasörü altındaki `cucumber-reports.html` dosyasına **sağ tıklayıp tarayıcınızda açmanız (Open in Browser)** yeterlidir.

**💡 İpucu: Belirli Senaryoları Çalıştırma (Tags)**
Hangi senaryonun çalıştırılacağını belirlemek için `TestRunner` sınıfındaki `tags` bölümünü güncelleyebilirsiniz.
* Buraya istediğiniz senaryonun etiketini (örneğin `@smoke`, `@login`) yazın.
* Mevcut etiketleri, `resources` dizini altındaki `.feature` dosyalarında her senaryonun en tepesinde bulabilirsiniz.

---
## ⚡️ Paralel Test Koşumu

Bu proje, zaman kazanmak amacıyla testleri aynı anda birden fazla tarayıcıda (Parallel Execution) çalıştıracak şekilde yapılandırılmıştır.

**Nasıl Ayarlanır?**

**1. TestRunner Ayarı (Ana Şalter):**
Paralel koşumun aktif olabilmesi için `src/test/java/com/shiftup/runners/TestRunner.java` sınıfında `parallel değerinin true` (`@Override
@DataProvider(parallel = true)`) olduğundan emin olun:

Aynı anda kaç testin koşulacağını `pom.xml` dosyasından yönetebilirsiniz:

1. `pom.xml` dosyasını açın.
2. `plugins` içerisinde `<threadCount>` etiketini bulun.
3.   Buradaki sayıyı değiştirerek paralellik seviyesini belirleyin 
(Örneğin `2` yaparsanız aynı anda `2` tarayıcı açılır).

*Not: Paralel koşum, terminalden `mvn clean test` komutu çalıştırıldığında otomatik olarak devreye girer.*


