window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del estudiante
    const formulario = document.querySelector('#update_pacientes_form');
    formulario.addEventListener('submit', function (event) {
        let pacienteId = document.querySelector('#paciente_id').value;

        //creamos un JSON que tendrá los datos del estudiante
        //a diferencia de un estudiante nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#Nombre').value,
            apellido: document.querySelector('#Apellido').value,
            matricula: document.querySelector('#Cedula').value,
            fechaIngreso: document.querySelector('#FechaIngreso').value,
            domicilio:{
                id: document.querySelector('#paciente_id').value,
                calle:  document.querySelector('#calle').value,
                numero:  document.querySelector('#numero').value,
                localidad:  document.querySelector('#localidad').value,
                provincia:  document.querySelector('#provincia').value,
            },
            email: document.querySelector('#Email').value,

        };
 
        //invocamos utilizando la función fetch la API estudiantes con el método PUT
        //que modificará al estudiante que enviaremos en formato JSON
        const url =  '/pacientes';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de un estudiante del listado
    //se encarga de llenar el formulario con los datos del estudiante
    //que se desea modificar
    function findBy(id) {
          const url =  '/pacientes'+"/buscar/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              //let paciente = data; //revision
              document.querySelector('#paciente_id').value = paciente.id;
              document.querySelector('#Nombre').value = paciente.nombre;
              document.querySelector('#Apellido').value= paciente.apellido;
              document.querySelector('#Cedula').value = paciente.cedula;
              document.querySelector('#FechaIngreso').value = paciente.fechaIngreso;
              document.querySelector('#Email').value = paciente.email;
  
            //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_pacientes_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }