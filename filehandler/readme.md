# Filehandler

Filehandler é uma aplicação que pode ler arquivos na pasta home/data/in do usuário e escreve cada linha em um tópico no kafka. 

## Requirements

- Java 11
- Apache Kafka + Zookeeper

## Installation

Basta clonar o repositório no GIT HUB

```bash
git clone https://github.com/amaroneto-1/filehandler.git
```

## Usage
Basta usar o gradle wrapper executando o comando:

```linux
./gradlew bootRun
```
Depois efetue um GET request para:
```linux
http://localhost:8080/start
```