import { mostrarMensaje, urlbase, limpiarCamposUser, validaUsuario } from "./utils.js";

// Valida los atributos del usuario, entonces lo guarda
$(document).ready(function () {
    $("#btn-user").click(function () {
        // Se recupera el valor de los campos
        const nombre = $.trim($("#name").val());
        const email = $.trim($("#email").val());
        const identification = $.trim($("#identification").val());
        const cellphone = $.trim($("#cellphone").val());
        const address = $.trim($("#address").val());
        const zone = $.trim($("#zone").val());
        const type = $("#typeUser").val();
        const password = $("#pass").val();
        const confirmar = $("#pass2").val();
        const id = $("#idUser").val();
        console.log(id, nombre, identification, address, cellphone, email, password, zone, type)

        if (validaUsuario(nombre, email, password, confirmar, identification, address, cellphone, zone) === false) {
            return;
        } else {
            const user = {
                id: id,
                name: nombre,
                identification: identification,
                address: address,
                cellPhone: cellphone,
                email: email,
                password: password,
                zone: zone,
                type: type
            };
            $.ajax({
                url: `${urlbase}/user/update`,
                type: "PUT",
                data: JSON.stringify(user),
                dataType: "json",
                headers: {
                    "Content-Type": "application/json",
                },
                statusCode: {
                    201: function () {
                        mostrarMensaje("Confirmacion", "Usuario editado exitosamente", false);
                        window.location = "http://localhost:8080/app/users"
                    },
                },
            }
            )
        }
    })
})
