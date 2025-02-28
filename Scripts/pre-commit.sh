#!/bin/sh

# Function to run ktlint checks
run_ktlint_checks() {
  echo "\n🚀 Chalo, ktlint se code check karte hain!"
  ./gradlew ktlintCheck --daemon > /tmp/ktlint-result
  KT_EXIT_CODE=$?

  if [ ${KT_EXIT_CODE} -ne 0 ]; then
      cat /tmp/ktlint-result
      rm /tmp/ktlint-result
      echo "\n*********************************************************************************"
      echo "     💥 Plese make sure karo ki tumne galti nahi ki ha  💥"
      echo "     💡 Hint: Error padho aur fix karo. 🛠️"
      echo "*********************************************************************************\n"
      exit ${KT_EXIT_CODE}
  else
      rm /tmp/ktlint-result
      echo "🎉 Wah! Tumhara code ktlint test pass kar gaya! Bahut badhiya! 🚀✨"
  fi
}

# Script chalao
run_ktlint_checks

exit 0
