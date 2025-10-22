<p align="right">
  <a href="#polski">🇵🇱 Polski</a> | <a href="#english">🇬🇧 English</a>
</p>

# 🧠 FitMindAI – Backend API

---

<h2 id="polski">🇵🇱 FitMindAI – Backend</h2>

**FitMindAI-backend** to wydajne i skalowalne REST API dla platformy e-commerce z AI. Odpowiada za obsługę użytkowników, produktów, koszyka, zamówień, płatności, integrację z chatbotem opartym na OpenAI oraz zarządzanie kontami sprzedawców. Backend umożliwia sprzedawcom dodawanie, edytowanie i usuwanie własnych produktów, monitorowanie sprzedaży oraz zarządzanie zamówieniami, co pozwala na pełną kontrolę nad prowadzonym biznesem.


---

### 🔐 Bezpieczeństwo

- Spring Security + JWT  
- Haszowanie haseł za pomocą BCrypt  
- Role i autoryzacja dostępu  

---

### ⚙️ Kluczowe funkcjonalności

- ✅ Rejestracja, logowanie, role użytkowników  
- 🛒 Zarządzanie produktami i koszykiem  
- 📦 Obsługa zamówień  
- 💳 Płatności przez Stripe  
- 🧾 Generowanie faktur PDF  
- 🤖 Chatbot AI (OpenAI API)  
- 🔍 Filtrowanie, wyszukiwanie, sortowanie  
- 🔗 Integracja z frontendem w React  

---

### 🧠 Struktura projektu

```
FitMindAI-backend/
├── config/        # Konfiguracje (JWT, CORS, Swagger, itd.)
├── controller/    # REST API
├── payload/       # DTO (Data Transfer Objects)
├── model/         # Encje JPA
├── repository/    # Repozytoria Spring Data
├── service/       # Logika biznesowa
├── util/          # Narzędzia (np. generator PDF)
├── exception/     # Globalna obsługa wyjątków
├── security/      # Konfiguracja bezpieczeństwa
└── FitMindAiApplication.java
```

---

### 📡 Dokumentacja API

Pełna dokumentacja dostępna w pliku `swagger.json`.

👉 Aby otworzyć:

1. Wejdź na [https://editor.swagger.io](https://editor.swagger.io)  
2. Kliknij **File → Import File**  
3. Wybierz plik `swagger.json` z repozytorium  

Możesz też zaimportować dokumentację do Postmana.

---

### 🛠️ Technologie

- Java 17  
- Spring Boot 3  
- Spring Security (JWT)  
- PostgreSQL  
- Hibernate (JPA)  
- Stripe API  
- OpenAI API  
- Lombok  
- Swagger / OpenAPI  
- ModelMapper  
- Maven  
- Google libphonenumber  

---

### 📥 Instalacja

```bash
git clone https://github.com/your-username/FitMindAI-backend.git
cd sb-ecom
```
Skonfiguruj bazę danych w application.yml:

```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecommerce
    username: twoj_user
    password: twoje_haslo
  jpa:
    hibernate:
      ddl-auto: update
```
### Ustaw zmienne środowiskowe

- `JWT_SECRET`  
- `STRIPE_API_KEY`  
- `OPENAI_API_KEY`  

---

### Uruchom aplikację

```bash
./mvnw spring-boot:run
```
📄 Licencja
Projekt na licencji MIT – możesz używać i modyfikować, także komercyjnie.

<h2 id="english">🇬🇧 FitMindAI – Frontend</h2>

**FitMindAI-backend** is a performant and scalable REST API for an AI-powered e-commerce platform. It handles user management, products, cart, orders, payments, integration with an OpenAI-based chatbot, and seller account management. The backend allows sellers to add, edit, and remove their own products, track sales, and manage orders, providing full control over their business.
---

### 🔐 Security

- Spring Security + JWT  
- Password hashing with BCrypt  
- Roles and access authorization  

---

### ⚙️ Key Features

- ✅ User registration, login, and roles  
- 🛒 Product and cart management  
- 📦 Order handling  
- 💳 Payments via Stripe  
- 🧾 PDF invoice generation  
- 🤖 AI Chatbot (OpenAI API)  
- 🔍 Filtering, searching, sorting  
- 🔗 Integration with React frontend  

---

### 🧠 Project Structure

```
FitMindAI-backend/
├── config/ # Configurations (JWT, CORS, Swagger, etc.)
├── controller/ # REST API controllers
├── payload/ # DTO (Data Transfer Objects)
├── model/ # JPA entities
├── repository/ # Spring Data repositories
├── service/ # Business logic
├── util/ # Utilities (e.g. PDF generator)
├── exception/ # Global exception handling
├── security/ # Security configuration
└── FitMindAiApplication.java
```

---

### 📡 API Documentation

Full documentation is available in the `swagger.json` file.

👉 To open:

1. Go to [https://editor.swagger.io](https://editor.swagger.io)  
2. Click **File → Import File**  
3. Select the `swagger.json` file from the repo  

You can also import it into Postman.

---

### 🛠️ Technologies

- Java 17  
- Spring Boot 3  
- Spring Security (JWT)  
- PostgreSQL  
- Hibernate (JPA)  
- Stripe API  
- OpenAI API  
- Lombok  
- Swagger / OpenAPI  
- ModelMapper  
- Maven  
- Google libphonenumber  

---

### 📥 Installation

```bash
git clone https://github.com/your-username/FitMindAI-backend.git
cd sb-ecom
```
Configure the database in application.yml:
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecommerce
    username: your_user
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
```
### Set enviroment variables

- `JWT_SECRET`  
- `STRIPE_API_KEY`  
- `OPENAI_API_KEY`  

---

Run the application
```
./mvnw spring-boot:run
```

📄 License
Project licensed under MIT — free to use and modify, including commercially.

<p align="center">
  👤 Author: <strong>Bartek Rafałko</strong><br>
  🔗 <a href="https://github.com/elfredoo/FitMindAI-frontend">Frontend Repository</a>
</p>
