# gcp-gcp-service

This project demonstrates the integration of Google Cloud Pub/Sub with a Quarkus application using Kotlin.
The application subscribes to a Pub/Sub topic, processes incoming messages,
and stores them in an H2 database. Additionally, it uploads artifacts to Google Cloud Artifact Registry.

# Technologies Used
- Quarkus: Framework for building Java applications.
- Kotlin: Programming language used for development.
- Google Cloud Pub/Sub: Messaging service for event-driven systems.
- Google Cloud Artifact Registry: Managed repository for storing artifacts.
- H2 Database: In-memory database for storing messages.
- Java 17: Programming language compatibility with Quarkus.
- Maven: Dependency management and build tool.

# Google Cloud Setup
1) Create a Google Cloud project and enable the Pub/Sub and Artifact Registry APIs.
2) Generate a service account key file in JSON format with Pub/Sub and Artifact Registry roles.
3) Update the application.properties file with the path to your service account key file and other Google Cloud configurations.
4) You can follow this steps :-[Google cloud console setup.docx](Google%20cloud%20console%20setup.docx)

# Key Features
- Pub/Sub Subscriber: Subscribes to a specified Pub/Sub topic for receiving messages.
- Message Processor: Processes incoming messages and stores them in the H2 database.
- Database Integration: Uses Hibernate ORM to interact with the H2 database.
- Google Cloud Integration: Utilizes Google Cloud SDK and dependencies for Pub/Sub and Artifact Registry interactions.
- Non-reactive: Implemented in a non-reactive style suitable for specific use cases.

# Configuration
Ensure that all necessary configurations are correctly set in the application.properties file, including:
- Google Cloud project ID.
- Path to the service account key JSON file.
- Pub/Sub subscription details.
- Artifact Registry repository information.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/com.gcp-pubsub-service-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
- Google Cloud Pubsub ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-google-cloud-services/main/pubsub.html)): Use Google Cloud PubSub messaging broker service
