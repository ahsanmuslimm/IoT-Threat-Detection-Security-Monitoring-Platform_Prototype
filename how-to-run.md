
# IoT Anomaly Detector & Security Monitoring Platform - How to Run

## Prerequisites

1. **Java Development Kit (JDK)**: Ensure you have JDK 8 or later installed.
2. **Maven**: Ensure Maven is installed for dependency management and running tests.

## Setup

### 2. Install Dependencies

```bash
# Install Java WebSocket library
mvn install:install-file -Dfile=Java-WebSocket.jar -DgroupId=org.java-websocket -DartifactId=Java-WebSocket -Dversion=1.5.1 -Dpackaging=jar
mvn install:install-file  # slf4j-api-2.0.7.jar
mvn install:install-file  # slf4j-simple-2.0.7.jar

#Run the File 
   Setting.json

# Install other dependencies
mvn clean install
```

## Running the Prototype

### 1. Run the Java Program

```bash
# Compile and run the main Java program
javac -cp ".:Java-WebSocket.jar" IoTAnomalyDetector.java
java -cp ".:Java-WebSocket.jar" IoTAnomalyDetector
```

### 2. Open the Dashboard

- Open the `dashboard.html` file in a web browser.
- The dashboard will connect to the WebSocket server and display real-time data.

## Running Tests

### 1. Run Automated Tests

```bash
# Run all tests using Maven
mvn test
```

### 2. Manual Black Box Testing

#### Test Case 1: Verify Packet Listener Receives Packets

```bash
# Run PacketListenerTest
mvn -Dtest=PacketListenerTest test
```

#### Test Case 2: Validate Feature Extraction

```bash
# Run FeatureExtractorTest
mvn -Dtest=FeatureExtractorTest test
```

#### Test Case 3: Test Behavior Analysis Logic

```bash
# Run BehaviorAnalyzerTest
mvn -Dtest=BehaviorAnalyzerTest test
```

#### Test Case 4: Evaluate Rule Engine Against Thresholds

```bash
# Run RuleEngineTest
mvn -Dtest=RuleEngineTest test
```

#### Test Case 5: Confirm Threat Detection Accuracy

```bash
# Run ThreatDetectorTest
mvn -Dtest=ThreatDetectorTest test
```

#### Test Case 6: Check Alert Generation for Threats

```bash
# Run AlertManagerTest
mvn -Dtest=AlertManagerTest test
```

#### Test Case 7: Validate WebSocket Data Broadcasting

```bash
# Run DashboardWebSocketServerTest
mvn -Dtest=DashboardWebSocketServerTest test
```

#### Test Case 8: Test Dashboard Connection to WebSocket

```bash
# Run DashboardConnectionTest
mvn -Dtest=DashboardConnectionTest test
```

#### Test Case 9: Verify Real-Time Chart Updates

```bash
# Run ChartUpdateTest
mvn -Dtest=ChartUpdateTest test
```

#### Test Case 10: Ensure Alert Display on Dashboard

```bash
# Run AlertDisplayTest
mvn -Dtest=AlertDisplayTest test
```

## Viewing the Dashboard

- Open `dashboard.html` in a web browser.
- Ensure the WebSocket server is running and connected.

## Troubleshooting

- Ensure all dependencies are installed.
- Check that the WebSocket server is running and accessible.
- Verify that the dashboard is opened in a compatible web browser.

