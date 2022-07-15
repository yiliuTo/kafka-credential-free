# Credential-free connection sample for Event Hubs for Kafka
This sample illustrates how to connect to Azure Event Hubs for Kafka without credentials via spring-kafka library.

## How to run
To run this sample, you need to do the following steps:
1. Complete the property `spring.kafka.bootstrap-servers` with the value of your Azure Event Hubs namespace name in [application.properties](./src/main/resources/application.properties)
2. Fill in the property `spring.kafka.template.default-topic` with the value of your Azure Event Hub name in [application.properties](./src/main/resources/application.properties), which will be used to send/receive messages with.
3. Fill in your Azure Event Hub name in [ReceiveService.java](./src/main/java/com/azure/spring/kafka/ReceiveService.java).

### Run it locally
To run it locally, you can use Azure CLI credential to connect to Azure Event Hubs. Please refer to below steps to setup Azure CLI credentials in your environment and configure it with necessary Azure Event Hubs data plane permissions.
1. Sign in to your Azure account by using the following command:
    ```shell
    az login
    ```
2. Use the following command and specify the GUID for the subscription you want to use with Azure:
    ```shell
    az account set --subscription <your-account-ID>
    ```
3. Configure the role of [Azure Event Hubs Data Sender](https://docs.microsoft.com/azure/role-based-access-control/built-in-roles#azure-event-hubs-data-send) and [Azure Event Hubs Data Receiver](https://docs.microsoft.com/azure/role-based-access-control/built-in-roles#azure-event-hubs-data-receiver) to your Azure account, which you can refer to [the doc about authorizing access with Azure AD](https://docs.microsoft.com/azure/event-hubs/authorize-access-azure-active-directory) and [the doc about assigning role via Azure portal](https://docs.microsoft.com/azure/role-based-access-control/role-assignments-portal?tabs=current).

   Note that besides the Azure CLI credential, this sample can also collect other types of your credentials from the environment, including Intellij credential and Visual Studio Code credential, please refer [here](https://aka.ms/spring/docs/4.3.0#authentication) for more details.

4. Build your Spring Boot application with Maven and run it; for example:
   ```shell
   mvn clean package -Dmaven.test.skip=true
   mvn spring-boot:run
   ```

5. Once your application is running, you can use curl to test your application; for example:
   ```shell
   curl -X POST http://localhost:8080/messages?message=hello
   ```
   you should see "hello" posted to your application's logs. For example:
   ```
   2022-07-15 16:08:36.274  INFO 12756 --- [nio-8080-exec-1] o.a.kafka.common.utils.AppInfoParser     : Kafka version: 3.0.1
   2022-07-15 16:08:36.274  INFO 12756 --- [nio-8080-exec-1] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId: 8e30984f43e64d8b
   2022-07-15 16:08:36.274  INFO 12756 --- [nio-8080-exec-1] o.a.kafka.common.utils.AppInfoParser     : Kafka startTimeMs: 1657872516274
   2022-07-15 16:08:38.449  INFO 12756 --- [ad | producer-1] o.a.k.c.p.internals.TransactionManager   : [Producer clientId=producer-1] ProducerId set to 0 with epoch 0
   2022-07-15 16:08:40.704  INFO 12756 --- [tial-free-0-C-1] c.a.s.k.ReceiveService                   : Received message from Event hub: hello
   ```
### Run in on Azure Spring Apps
To run this sample on Azure Spring Apps, you can refer to [this doc](https://docs.microsoft.com/azure/developer/java/spring-framework/configure-spring-cloud-stream-binder-java-app-kafka-azure-event-hub#deploy-to-azure-spring-apps)