#!/bin/bash



# Function to run ktlint checks
run_ktlint_checks() {
  echo "\n🚀 Brace yourself! We're about to embark on a journey of code analysis and style checking with ktlint!"
  ./gradlew ktlintCheck --daemon > /tmp/ktlint-result
  KT_EXIT_CODE=$?

  if [ ${KT_EXIT_CODE} -ne 0 ]; then
      cat /tmp/ktlint-result
      rm /tmp/ktlint-result
      echo "\n*********************************************************************************"
      echo "     💥 Oh no! ktlint found style issues in the code! Time to fix those gremlins! 💥"
      echo "     💡 Tip: You might need your Kotlin ninja skills to resolve these issues. 🛠️"
      echo "*********************************************************************************\n"
      exit ${KT_EXIT_CODE}
  else
      rm /tmp/ktlint-result
      echo "🎉 Bravo! Your Kotlin code has passed ktlint's rigorous style checks with flying colors! Keep rocking that clean code! 🚀💫"
  fi
}


:


# Function to run Spotless checks
run_spotless_checks() {
  echo "\n🚀 Spotless is now analyzing and formatting your code!"
  ./gradlew spotlessApply --daemon > /tmp/spotless-result
  SPOTLESS_EXIT_CODE=$?

  if [ ${SPOTLESS_EXIT_CODE} -ne 0 ]; then
      cat /tmp/spotless-result
      rm /tmp/spotless-result
      echo "\n*********************************************************************************"
      echo "   💥 Uh-oh! Spotless found formatting issues in the code! Time to tidy up! 💥"
      echo "      💡 Tip: Check the reported issues and fix formatting errors. 🛠️"
      echo "*********************************************************************************"
      exit ${SPOTLESS_EXIT_CODE}
  else
      rm /tmp/spotless-result
      echo "🎉 Stellar job! Your code is pristine and has passed Spotless's formatting checks without a hitch! Keep shining bright! ✨🚀"
  fi
}

# Function ko call karo
run_spotless_checks
