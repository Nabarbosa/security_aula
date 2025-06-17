// src\components\AtualizarUsuario\index.js

import { useState } from "react";
import './style.css';
import { useNavigate } from "react-router-dom";
import useMensagem from '../../hooks/useMensagem';
import MensagemFeedback from '../MensagemFeedback';
import logo from '../../assets/images/logo.png';
import axios from 'axios';

function FormularioAtualizacao() {
    const [email, setEmail] = useState('');
    const [nome, setNome] = useState('');
    const [senha, setSenha] = useState('');
    const navigate = useNavigate();
    const { exibirMensagem, mensagem, tipoMensagem, visivel, fecharMensagem } = useMensagem();

    const atualizarUsuario = async () => {
        try {
            const response = await axios.put(`http://localhost:8080/usuarios/atualizar/${email}`, { nome, senha });
            exibirMensagem(response.data.mensagem || 'Usuário atualizado com sucesso!', 'sucesso');
            setEmail('');
            setNome('');
            setSenha('');
        } catch (error) {
            let erroMsg = 'Erro ao conectar ao servidor.';
            if (error.response && error.response.data) {
                erroMsg = error.response.data.mensagem;
                if (error.response.data.erros) {
                    erroMsg += ' ' + Object.values(error.response.data.erros).join(', ');
                }
            }
            exibirMensagem(erroMsg, 'erro');
        }
    };

    return (
        <div className="container-atualizar">
            <img src={logo} alt="Logo da empresa" />
            <h2>Atualização de usuário</h2>
            <form onSubmit={(e) => { e.preventDefault(); atualizarUsuario(); }}>
                <input
                    type="email"
                    id="email"
                    placeholder="E-mail"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
                
                <input
                    type="text"
                    id="nome"
                    placeholder="Nome"
                    value={nome}
                    onChange={(e) => setNome(e.target.value)}
                    required
                />
                <input
                    type="password"
                    id="senha"
                    placeholder="Senha (deixe em branco para não alterar)"
                    value={senha}
                    onChange={(e) => setSenha(e.target.value)}
                />
                <button type="submit">Atualizar</button>
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

export default FormularioAtualizacao;
