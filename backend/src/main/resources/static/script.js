document.getElementById("usuarioForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Evita que el formulario se envíe de manera tradicional

    const nombre = document.getElementById("nombre").value;
    const primerApellido = document.getElementById("primerApellido").value;
    const segundoApellido = document.getElementById("segundoApellido").value;
    const nombreUsuario = document.getElementById("nombreUsuario").value;

    const usuario = {
        nombre: nombre,
        primerApellido: primerApellido,
        segundoApellido: segundoApellido,
        nombreUsuario: nombreUsuario
    };

    fetch("http://localhost:8080/usuarios/crear", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(usuario) // Convierte el objeto a JSON
    })
        .then(response => response.json()) // Convierte la respuesta JSON
        .then(data => {
            alert("Usuario creado exitosamente");
            console.log(data); // Muestra el usuario creado en consola
        })
        .catch(error => {
            console.error("Error al crear usuario:", error);
            alert("Hubo un error al crear el usuario.");
        });
});
