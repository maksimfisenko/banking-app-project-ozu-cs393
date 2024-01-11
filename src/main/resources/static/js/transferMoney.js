document.addEventListener("DOMContentLoaded", function (){

    const accountSelect = document.getElementById("sending-account");
    const amountLabel = document.getElementById("sending-account-amount");

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
        const accountSelect = document.getElementById("sending-account");

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

    document.getElementById("transfer-money-form").addEventListener("submit", function (event) {

        event.preventDefault();

        const sendingAccountSelect = document.getElementById("sending-account");
        const sendingAccountNumber = sendingAccountSelect.options[sendingAccountSelect.selectedIndex].value;

        const apiUrl = `accounts/${sendingAccountNumber}`;

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {

                const receivingAccountNumber = document.getElementById("receiving-account").value;
                const amount = document.getElementById("amount").value;

                if (sendingAccountNumber !== receivingAccountNumber) {
                    if (amount > 0) {
                        const dataJson = {
                            sendingAccountNumber: sendingAccountNumber,
                            receivingAccountNumber: receivingAccountNumber,
                            amount: amount
                        };
                        fetch("/accounts/transfer", {
                            method: "PUT",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify(dataJson)
                        })
                            .then(response => response.json())
                            .then(dataJson => {
                                console.log(dataJson);
                            })
                            .catch(error => {
                                console.log(error);
                            });
                        alert("The money has been transferred successfully!");
                        fetchAccountAmount(sendingAccountNumber);
                    } else {
                        alert("You cannot transfer zero or less amount.")
                    }
                } else {
                    alert("You cannot transfer money to the same account.");
                }
            })
            .catch(error => console.error("Error transferring money:", error));
    });
});