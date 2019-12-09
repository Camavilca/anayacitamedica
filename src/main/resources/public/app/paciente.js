Vue.component("modal", {template: "#modal_template"});
Vue.component('multiselect', {
    mixins: [window.VueMultiselect.default],
    props: {selectLabel: {default: ""}}
});
new Vue({
    el: "#pacienteVUE",
    data: {
        paciente: {},
        pacientes: [],
        pacienteModal: {
            id: "pacienteModal",
            header: true,
            title: 'Nuevo Paciente',
            okbtn: 'Aceptar',
            modalSize: 'modal-lg',
            processing: false
        },
        allURL: APP.url('paciente/allDynatable')
    },
    mounted() {
        const $vue = this;
        $vue.$refs.load.repreload();
    },
    methods: {
        openModalPaciente() {
            let $vue = this;
            $vue.paciente = {};
            $vue.$refs.pacienteModal.open();
        },
        save() {
            let $vue = this;
            axios.post("/paciente/save", $vue.paciente).then(response => {
                if (response.data.success) {
                    $vue.$refs.load.repreload();
                    $vue.$refs.pacienteModal.close();
                    notify2(response.data.message, "success");
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        eliminar(paciente) {
            let $vue = this;
            swal.fire({
                title: "¿ Seguro que quieres Eliminar ?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Aceptar"
            }).then(result => {
                if (result.value) {
                    axios.post("/paciente/delete", paciente).then(response => {
                        if (response.data.success) {
                            $vue.$refs.load.repreload();
                            notify2(response.data.message, "success");
                        } else {
                            notify2(response.data.message, "error");
                        }
                    }).catch((err) => {
                        notify2(MESSAGES.errorComunicacion, "error");
                    });
                }
            });
        },
        update(paciente) {
            const $vue = this;
            $vue.pacienteModal.title = `Editar ${paciente.nombre}`;
            $vue.openModalPaciente();
            $vue.paciente = paciente;
        }
    }
});