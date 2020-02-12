#Running App

To run app make sure, you have installed JDK 8+ and Maven Tool.

1. To built App package, clone this repository and in project catalogue with command line insert: "mvn package".
2. To run app use command "java -jar tydzien1pd-0.0.1-SNAPSHOT.jar --spring.profiles.active= *__"profile"__* --language.languageVersion = *__"language version"__*
where:
- you can choose proper profile: "start", "plus", "pro". If you skip this parameter, app will run in start mode as default
- you can choose proper language version" "en" or "pl". If you skip this parameter, app will run in English language as default.
