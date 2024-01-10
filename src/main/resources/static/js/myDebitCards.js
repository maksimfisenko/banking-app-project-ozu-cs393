document.addEventListener("DOMContentLoaded", function (){

    fetchUserAccounts();
    function fetchUserAccounts() {

        const userId = 1;
        const apiUrl = `accounts/user/${userId}`;

        const cardAccountSelect = document.getElementById("choose_account");

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                data.forEach(account => {
                    const option = document.createElement("option");
                    option.value = account.id;
                    option.text = account.name;
                    cardAccountSelect.appendChild(option);
                });
            })
            .catch(error => console.error("Error fetching accounts:", error));
    }
});