# Shree Anna Marketplace Platform

A full-stack marketplace platform designed to connect millet farmers, FPOs, processors, startups, and buyers in a single digital ecosystem.  
Built using **Java Servlets, JSP, JDBC, MySQL, HTML, CSS, and JavaScript**, the platform supports role-based access, product uploads with images, cart management, demo UPI payment flow, invoice download, and email order confirmation.

---

## Features

- Role-based login system (Farmer, Buyer, Processor, Startup)  
- Shopping cart functionality  
- Product listing with image upload  
- Demo UPI payment integration  
- Secure authentication using Servlets + Session Management  
- Persistent storage using MySQL Database  

---

## Tech Stack

| Layer | Technologies Used |
|-------|-------------------|
| Frontend | HTML5, CSS3, JavaScript |
| Backend | Java Servlets, JSP, JDBC |
| Database | MySQL |
| Build Tool | Maven |
| Server | Apache Tomcat |
| Version Control | Git & GitHub |

---

## Project Structure

├── src/main/java/com/shreeanna/
│ ├── dao/
│ ├── model/
│ └── servlet/
├── src/main/webapp/
│ ├── images/
│ ├── uploads/
│ ├── css/
│ └── *.jsp
├── sql/
│ └── create_tables.sql
├── pom.xml
└── README.md

---

## 🔧 Setup & Installation

### Prerequisites

- Java 8 or above  
- Apache Tomcat (9+ recommended)  
- MySQL Server  
- Maven  
- Eclipse / IntelliJ (optional)

### Steps

1. Clone repository:
   git clone https://github.com/sanjaysrinivas021/ShreeAnnaMarketplace.git
   Import project as Maven Project into Eclipse/IntelliJ.
2. Configure database using sql/create_tables.sql.
3. Update DB credentials in DBUtil.java.
4. Deploy to Tomcat and run:
   http://localhost:8080/ShreeAnnaMarketplace/

### Demo Credentials

| Role   | Username                                  | Password |
| ------ | ----------------------------------------- | -------- |
| Farmer | [farmer@test.com](mailto:farmer@test.com) | 1234     |
| Buyer  | [buyer@test.com](mailto:buyer@test.com)   | 1234     |

---

## Future Enhancements

- Live UPI/Razorpay integration
- Fully responsive UI
- Review & rating system
- Logistics tracking API

---

## License

This project is licensed under the MIT License — free to use and improve.

---

## Author

Sanjay Srinivas
GitHub: https://github.com/sanjaysrinivas021

---



