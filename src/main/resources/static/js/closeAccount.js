document.addEventListener("DOMContentLoaded", function (){

    const accountSelect = document.getElementById("account");
    const amountLabel = document.getElementById("account-amount");

    fetchUserAccounts();

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
        const accountSelect = document.getElementById("account");

        accountSelect.innerHTML = '';

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

    document.getElementById("close-account-form").addEventListener("submit", function (event) {

        event.preventDefault();

        const accountSelect = document.getElementById("account");
        const accountNumber = accountSelect.options[accountSelect.selectedIndex].value;

        const apiUrl = `accounts/${accountNumber}`;

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {

                const r = /\d+/;
                const amount = parseInt(amountLabel.textContent.match(r)[0]);

                alert(accountNumber);

                if (amount === 0) {
                    const dataJson = {
                        accountNumber: accountNumber,
                    };
                    fetch("/accounts/close", {
                        method: "DELETE",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify(dataJson)
                    })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error(`HTTP error! Status: ${response.status}`);
                            }
                            return response.text();
                        })
                        .catch(error => {
                            console.log(error);
                        });
                    alert("The account is closed successfully!");
                    fetchUserAccounts();
                } else {
                    alert("You cannot close account with positive amount on balance.")
                }
            })
            .catch(error => console.error("Error closing account:", error));
    });
});