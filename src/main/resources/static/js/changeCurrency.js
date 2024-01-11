document.addEventListener("DOMContentLoaded", function (){

    const accountSelect = document.getElementById("currency-account");
    const amountLabel = document.getElementById("amount");

    fetchUserAccounts();
    fetchCurrencies();

    accountSelect.addEventListener("change", function () {
        const selectedAccountNumber = accountSelect.options[accountSelect.selectedIndex].value;
        fetchAccountAmount(selectedAccountNumber);
    });

    function fetchAccountAmount(accountNumber) {

        const apiUrl = `/accounts/${accountNumber}`;

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                amountLabel.textContent = `Current amount: ${data.amount} ${data.currency.symbol} (${data.currency.name})`;
            })
            .catch(error => console.error("Error fetching account amount:", error));
    }

    function fetchUserAccounts() {

        const userId = 1;
        const apiUrl = `accounts/user/${userId}`;

        const accountSelect = document.getElementById("currency-account");

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                data.forEach(account => {
                    const option = document.createElement("option");
                    option.value = account.number;
                    option.text = account.name;
                    accountSelect.appendChild(option);
                });

                if (data.length > 0) {
                    const initialAccountNumber = data[0].number;
                    fetchAccountAmount(initialAccountNumber);
                }

            })
            .catch(error => console.error("Error fetching accounts:", error));
    }

    function fetchCurrencies() {

        const apiUrl = `/currencies`;

        const currencySelect = document.getElementById("currency");

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                data.forEach(currency => {
                    const option = document.createElement("option");
                    option.value = currency.id;
                    option.text = `${currency.symbol} (${currency.name})`;
                    currencySelect.appendChild(option);
                });

            })
            .catch(error => console.error("Error fetching currencies:", error));
    }

    document.getElementById("change-currency-form").addEventListener("submit", function (event) {

        event.preventDefault();

        const accountSelect = document.getElementById("currency-account");
        const accountNumber = accountSelect.options[accountSelect.selectedIndex].value;

        const apiUrl = `accounts/${accountNumber}`;

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {

                const currentCurrencyId = data.currency.id;
                const currencySelect = document.getElementById("currency");
                const newCurrencyId = currencySelect.options[currencySelect.selectedIndex].value;

                if (newCurrencyId != currentCurrencyId) {

                    const data = {
                        accountNumber: accountNumber,
                        currencyId: newCurrencyId
                    };

                    fetch("/accounts/changeCurrency", {
                        method: "PUT",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify(data)
                    })
                        .then(response => response.json())
                        .then(data => {
                            console.log(data);
                        })
                        .catch(error => {
                            console.log(error);
                        });
                    alert("The account currency has been successfully changed!");
                    fetchAccountAmount(accountNumber);
                } else {
                    alert("You account already has this currency!");
                }
            })
            .catch(error => console.error("Error fetching account:", error));
    });
});