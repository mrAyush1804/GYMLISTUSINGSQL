#!/bin/sh

# Function to run ktlint checksi
run_ktlint_checks() {
  # shellcheck disable=SC2028
  echo "\n🚀 Brace yourself! We're about to embark on a journey of code analysis and style checking with ktlint!"
  ./gradlew ktlintCheck --daemon > /tmp/ktlint-result
  KT_EXIT_CODE=$?

  if [ ${KT_EXIT_CODE} -ne 0 ]; then
      cat /tmp/ktlint-result
      rm /tmp/ktlint-result
      # shellcheck disable=SC2028
      echo "\n*********************************************************************************"
      echo "     💥 Oh no! ktlint found style issues in the code! Time to fix those gremlins! 💥"
      echo "     💡 Tip: You might need your Kotlin ninja skills to resolve these issues. 🛠️"
      # shellcheck disable=SC2028
      echo "*********************************************************************************\n"
      exit ${KT_EXIT_CODE}
  else
      rm /tmp/ktlint-result
      echo "🎉 Bravo! Your Kotlin code has passed ktlint's rigorous style checks with flying colors! Keep rocking that clean code! 🚀💫"
  fi
}

# Script chalao
run_ktlint_checks

exit 0
