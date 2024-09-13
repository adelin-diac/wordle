# Wordle

https://wordle.adelindiac.site

## Getting Started

**Don't use Docker yet. The I am still working on getting the `docker-compose.dev.yaml` file to work correctly to allow for development of this project in Docker.**

For now, follow these steps:

1. Ensure you have the JDK 21 installed.
2. Open two terminals, and navigate to `./wordle/v2_with_server/wordle`
3. In one terminal, run `./gradlew -t bootJar` - this rebuilds the files when you've made any changes.
4. In the second terminal, run `./gradlew bootRun` - this starts the application on port 8080.
5. You can now view the app on http://localhost:8080 (to view changes to the index.html file, you must refresh the browser.)
