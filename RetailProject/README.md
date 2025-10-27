# 🛍️ RetailProject – Kafka-Based Microservices Communication

This project demonstrates a **microservices-based architecture** using **Spring Boot** and **Apache Kafka** for asynchronous communication between two services:

* **InventoryService33** – acts as a **Kafka Producer**
* **OrderService33** – acts as a **Kafka Consumer**

---

## 📁 Project Structure

```
RetailProject/
│
├── InventoryService33/
│   ├── src/main/java/com/inventory/
│   │   ├── InventoryService33Application.java
│   │   └── controller/InventoryController.java
│   └── src/main/resources/application.properties
│
├── OrderService33/
│   ├── src/main/java/com/order/
│   │   ├── OrderService33Application.java
│   │   ├── controller/OrderController.java
│   │   └── service/Inventory.java
│   └── src/main/resources/application.properties
│
└── pom.xml
```

---

## ⚙️ Technologies Used

* **Java 17+**
* **Spring Boot 3+**
* **Spring Kafka**
* **Apache Kafka (local setup)**
* **Maven**

---

## 🚀 How It Works

1. **InventoryService33** exposes an API endpoint:

   ```
   POST /order/create
   ```

   It produces messages (order details) and sends them to Kafka topic **`Inventory-topic`**.

2. **OrderService33** listens to the **`Inventory-topic`** via a Kafka consumer and stores incoming messages in memory.

3. You can fetch the received messages via:

   ```
   GET /api/orders
   ```

---

## 🧩 Configuration

### **InventoryService33 – `application.properties`**

```properties
spring.application.name=InventoryService33
server.port=8082

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

topic.Inventory=Inventory-topic
```

### **OrderService33 – `application.properties`**

```properties
spring.application.name=OrderService33
server.port=8083

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=Inventory_group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer

topic.inventory=Inventory-topic
```

---

## 🧠 Kafka Setup Commands (Windows CMD)

### 1. Navigate to Kafka directory

```bash
cd kafka
```

### 2. Generate a UUID for storage

```bash
.\bin\windows\kafka-storage.bat random-uuid
```

### 3. Format the Kafka log directory (example)

```bash
.\bin\windows\kafka-storage.bat format -t <UUID_FROM_ABOVE> -c .\config\kraft\broker.properties
```

### 4. Start Kafka Server

```bash
.\bin\windows\kafka-server-start.bat .\config\broker.properties
```

### 5. Create the topic `Inventory-topic`

```bash
.\bin\windows\kafka-topics.bat --create --topic Inventory-topic --bootstrap-server localhost:9092
```

### 6. Verify topic creation

```bash
.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
```

---

## 🧪 Running the Services

1. **Start Kafka** using the commands above.
2. Run **InventoryService33** (`server.port=8082`).
3. Run **OrderService33** (`server.port=8083`).
4. Send a POST request:

   ```bash
   POST http://localhost:8082/order/create
   Content-Type: application/json
   Body: "Order-12345"
   ```
5. Check the consumer output in **OrderService33** console:

   ```
   Received Order: Order-12345
   ```
6. Fetch all orders:

   ```
   GET http://localhost:8083/api/orders
   ```

---

## ✅ Expected Output

**InventoryService33 Console:**

```
Order placed Successfully
```

**OrderService33 Console:**

```
Received Order: Order-12345
```

---

## 🧾 Notes

* Ensure Kafka is running before starting either service.
* Update topic names consistently in both services (`Inventory-topic`).
* Use different ports to avoid conflicts between services.
* You can modify topic configuration in each `application.properties` file.

---

**Author:** Moksh Oswal

