window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del estudiante
    const formulario = document.querySelector('#update_turnos_form');
    formulario.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;
    
        //creamos un JSON que tendrá los datos del estudiante
        //a diferencia de un estudiante nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            
            paciente:{
                id:document.querySelector('#pacienteId').value,
            }, 
            odontologo: {
                id:document.querySelector('#odontologiId').value,
            }, 
            fecha: document.querySelector('#fecha').value,
            
        };

        //invocamos utilizando la función fetch la API estudiantes con el método PUT
        //que modificará al estudiante que enviaremos en formato JSON
        const url =  '/turnos';
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
          const url =  '/turnos'+"/buscar/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data; //revision
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#pacienteId').value = turno.pacienteId;
              document.querySelector('#odontologoId').value = turno.odontologoId;
              document.querySelector('#fecha').value= turno.fecha;
  
            //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_turnos_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }