const requestURL = 'http://localhost:8080/api/admin';

const usersTableNavLink = document.getElementById("horizontal_navigation-users_table");
const newUserNawLink    = document.getElementById("horizontal_navigation-new_user");
const allUsersTable     = document.querySelector(".all-users-table");


//Таблица со всеми юзерами

// Генерация кода для заполнения таблицы данными обо всех юзерах
const renderUsers = (users) => {
    if (users.length > 0) {
        let temp = '';
        users.forEach((user) => {
            temp += `
                <tr>
                    <td> ${user.id} </td>
                    <td> ${user.name} </td>
                    <td> ${user.surname} </td>
                    <td> ${user.age} </td>
                    <td> ${user.username} </td>
                    <td> ${user.roles.map((role) => role.name === "ROLE_USER" ? " ROLE_USER" : "ROLE_ADMIN")} </td>
                    <td> <button type="button" class="btn btn-info" id="btn-edit-modal-call" data-toggle="modal" data-target="modal-edit"
                    data-id="${user.id}">Edit</button></td>
                    <td> <button type="button" class="btn btn-danger" id="btn-delete-modal-call" 
                    data-id="${user.id}">Delete</button></td>
                </tr>
        `
        })
        allUsersTable.innerHTML = temp;
    }
}

// Получение данных всех пользователей с помощью fetch и заполнение таблицы с помощью renderUsers
function getAllUsers () {
    fetch(requestURL, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .then(data => {
            renderUsers(data);
        })
}

// Вызов функции
getAllUsers();

//Добавление нового юзера

//Форма добавления юзера
const addUserForm         = document.querySelector(".add-user-form");
// Поля формы добавления нового юзера
const addUserFormName     = document.getElementById("add-user-form-name");
const addUserFormSurname = document.getElementById("add-user-form-surname");
const addUserFormAge      = document.getElementById("add-user-form-age");
const addUserFormUsername    = document.getElementById("add-user-form-username");
const addUserFormPassword = document.getElementById("add-user-form-password");
const addUserFormRoles    = document.getElementById("add-user-form-roles");
//Кнопка submit формы нового юзера
const addButtonSubmit     = document.getElementById("add-btn-submit");

//Генерация ролей
function getRolesFromAddUserForm() {
    let roles = Array.from(addUserFormRoles.selectedOptions)
        .map(option => option.value);
    let rolesToAdd = [];
    if (roles.includes("1")) {
        let role1 = {
            id: 1,
            name: "ROLE_ADMIN"
        }
        rolesToAdd.push(role1);
    }
    if (roles.includes("2")) {
        let role2 = {
            id: 2,
            name: "ROLE_USER"
        }
        rolesToAdd.push(role2);
    }
    return rolesToAdd;
}

addUserForm.addEventListener("submit", (e) => {
    e.preventDefault();
    fetch(requestURL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: addUserFormName.value,
            surname: addUserFormSurname.value,
            age: addUserFormAge.value,
            username: addUserFormUsername.value,
            password: addUserFormPassword.value,
            roles: getRolesFromAddUserForm()
        })
    })
        .then(() => {
            usersTableNavLink.click();
            location.reload();
        });
})

//Удаление и изменение юзеров

const modalEditExitBtn      = document.getElementById("exit_btn-modal-edit");
const modalEditCloseBtn     = document.getElementById("close_btn-modal-edit");
const modalEditSubmitBtn    = document.getElementById("submit_btn-modal-edit");
const editUsersRoles        = document.getElementById("edit-rolesSelect");
const editRoleAdminOption   = document.getElementById("edit-role_admin");
const editRoleUserOption    = document.getElementById("edit-role_user");

const deleteRoleAdminOption = document.getElementById("delete-role_admin");
const deleteRoleUserOption  = document.getElementById("delete-role_user");
const modalDeleteSubmitBtn  = document.getElementById("submit_btn-modal-delete");
const modalDeleteExitBtn    = document.getElementById("exit_btn-modal-delete");
const modalDeleteCloseBtn   = document.getElementById("close_btn-modal-delete");


let getDataOfCurrentUser = (id) => {
    return fetch(requestURL + "/" + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
        .then(res => res.json())
        .then(dataUser => {
            let user = {
                id: dataUser.id,
                name: dataUser.name,
                surname: dataUser.surname,
                age: dataUser.age,
                username: dataUser.username,
                password: dataUser.password,
                roles: dataUser.roles
            }
            console.log(user)
        })
}

function getRolesFromEditUserForm() {
    let roles = Array.from(editUsersRoles.selectedOptions)
        .map(option => option.value);
    let rolesToEdit = [];
    if (roles.includes("1")) {
        let role1 = {
            id: 1,
            name: "ROLE_ADMIN"
        }
        rolesToEdit.push(role1);
    }
    if (roles.includes("2")) {
        let role2 = {
            id: 2,
            name: "ROLE_USER"
        }
        rolesToEdit.push(role2);
    }
    return rolesToEdit;
}

//Отслеживание нажатий по кнопкам Edit и Delete в таблице юзеров
allUsersTable.addEventListener("click", e => {
    e.preventDefault();
    let delButtonIsPressed  = e.target.id === 'btn-delete-modal-call';
    let editButtonIsPressed = e.target.id === 'btn-edit-modal-call';

//Удаление юзеров

    const deleteUsersId       = document.getElementById("delete-id")
    const deleteUsersName     = document.getElementById("delete-name")
    const deleteUsersSurname = document.getElementById("delete-surname")
    const deleteUsersAge      = document.getElementById("delete-age")
    const deleteUsersUsername    = document.getElementById("delete-username")

    if (delButtonIsPressed) {
        let currentUserId = e.target.dataset.id;
        fetch(requestURL + "/" + currentUserId, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })
            .then(res => res.json())
            .then(user => {
                deleteUsersId.value       = user.id;
                deleteUsersName.value     = user.name;
                deleteUsersSurname.value = user.surname;
                deleteUsersAge.value      = user.age;
                deleteUsersUsername.value    = user.username;

                let deleteRoles = user.roles.map(i => i.roleName)
                deleteRoles.forEach(
                    role => {
                        if (role === "ROLE_ADMIN") {
                            deleteRoleAdminOption.setAttribute('selected', "selected");

                        } else if (role === "ROLE_USER") {
                            deleteRoleUserOption.setAttribute('selected', "selected");
                        }
                    })
            })
        $('#modal-delete').modal('show');

        modalDeleteSubmitBtn.addEventListener("click", e => {
            e.preventDefault();
            fetch(`${requestURL}/${currentUserId}`, {
                method: 'DELETE',
            })
                .then(res => res.json());
            modalDeleteExitBtn.click();
            console.log(`${requestURL}/${currentUserId}`);
            getAllUsers();
            location.reload();
        })
    }

//Изменение юзеров

    const editUsersId       = document.getElementById("edit-id");
    const editUsersName     = document.getElementById("edit-name");
    const editUsersSurname = document.getElementById("edit-surname");
    const editUsersAge      = document.getElementById("edit-age");
    const editUsersUsername    = document.getElementById("edit-username");

    if (editButtonIsPressed) {
        let currentUserId = e.target.dataset.id;
        fetch(requestURL + "/" + currentUserId, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })
            .then(res => res.json())
            .then(user => {

                editUsersId.value       = user.id;
                editUsersName.value     = user.name;
                editUsersSurname.value = user.surname;
                editUsersAge.value      = user.age;
                editUsersUsername.value    = user.username;

                let editRoles = user.roles.map(i => i.roleName)
                editRoles.forEach(
                    role => {
                        if (role === "ROLE_ADMIN") {
                            editRoleAdminOption.setAttribute('selected', "selected");

                        } else if (role === "ROLE_USER") {
                            editRoleUserOption.setAttribute('selected', "selected");
                        }
                    })
            })
        $('#modal-edit').modal('show');

        modalEditSubmitBtn.addEventListener("click", e => {
            e.preventDefault();
            let user = {
                id: editUsersId.value,
                name: editUsersName.value,
                surname: editUsersSurname.value,
                age: editUsersAge.value,
                username: editUsersUsername.value,
                roles: getRolesFromEditUserForm()
            }
            fetch(`${requestURL}/${currentUserId}`, {
                method: 'PUT',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify(user)
            })
                .then(res => console.log(res));
            modalEditExitBtn.click();
            getAllUsers();
            location.reload();
        })
    }
})

//Обработка закрытия модального окна edit
let removeSelectedRolesFromEditDoc = () => {
    if (editRoleAdminOption.hasAttribute('selected')) {
        editRoleAdminOption.removeAttribute('selected')
    }
    if (editRoleUserOption.hasAttribute('selected')) {
        editRoleUserOption.removeAttribute('selected')
    }
}
modalEditExitBtn.addEventListener("click", e => {
    e.preventDefault();
    removeSelectedRolesFromEditDoc();
})
modalEditCloseBtn.addEventListener("click", e => {
    e.preventDefault();
    removeSelectedRolesFromEditDoc();
})

//Обработка закрытия модального окна delete
let removeSelectedRolesFromDeleteDoc = () => {
    if (deleteRoleAdminOption.hasAttribute('selected')) {
        deleteRoleAdminOption.removeAttribute('selected')
    }
    if (deleteRoleUserOption.hasAttribute('selected')) {
        deleteRoleUserOption.removeAttribute('selected')
    }
}
modalDeleteExitBtn.addEventListener("click", e => {
    e.preventDefault();
    removeSelectedRolesFromDeleteDoc();
})
modalDeleteCloseBtn.addEventListener("click", e => {
    e.preventDefault();
    removeSelectedRolesFromDeleteDoc();
})


//Заполнение панели юзера

const userPanelData      = document.getElementById("user_panel-data");
const authorisedUserData = document.getElementById("authorised_user-data");

let currentUser = () => {
    fetch ("http://localhost:8080/api/user", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .then(user => {
            if (user != null) {
                userPanelData.innerHTML = `
                    <tr>
                        <td> ${user.id} </td>
                        <td> ${user.name} </td>
                        <td> ${user.surname} </td>
                        <td> ${user.age} </td>
                        <td> ${user.username} </td>
                        <td> ${user.roles.map((role) => role.name === "ROLE_USER" ? " ROLE_USER" : " ROLE_ADMIN")} </td>
                    </tr>
                `
                authorisedUserData.innerHTML = `
                    <p class="d-inline font-weight-bold">${user.username} with role ${user.roles.map((role) => role.name === "ROLE_USER" ? " User" : " Admin")}</p>`
            }
        })
}
currentUser();
