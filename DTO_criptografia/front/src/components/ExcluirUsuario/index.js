import { useState } from "react";
import './style.css';
import { useNavigate } from "react-router-dom";
import useMensagem from '../../hooks/useMensagem';
import MensagemFeedback from '../MensagemFeedback';
import logo from '../../assets/images/logo.png';
import axios from 'axios';

function ExcluirUsuario() {
    const [email, setEmail] = useState('');
    const navigate = useNavigate();
    const { exibirMensagem, mensagem, tipoMensagem, visivel, fecharMensagem } = useMensagem();

    const excluirUsuario = async () => {
        try {
            const response = await axios.delete(`http://localhost:8080/usuarios/${email}`);
            exibirMensagem(response.data.mensagem || 'Usuário excluído com sucesso!', 'sucesso');
            setEmail('');
        } catch (error) {
            let erroMsg = 'Erro ao excluir usuário.';
            if (error.response?.data) {
                erroMsg = error.response.data.mensagem;
            }
            exibirMensagem(erroMsg, 'erro');
        }
    };

    return (
        <div className="container-excluir">
            <img src={logo} alt="Logo da empresa" />
            <h2>Excluir Usuário</h2>
            <form onSubmit={(e) => { e.preventDefault(); excluirUsuario(); }}>
                <input
                    type="email"
                    placeholder="E-mail do usuário"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
                <button type="submit" className="botao-excluir">Excluir</button>
            </form>

            <button onClick={() => navigate('/usuarios')} className="link-usuarios">
                Ver usuários cadastrados
            </button>

            <MensagemFeedback
                mensagem={mensagem}
                tipo={tipoMensagem}
                visivel={visivel}
                onclose={fecharMensagem}
            />
        </div>
    );
}

export default ExcluirUsuario;
