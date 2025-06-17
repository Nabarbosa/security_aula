import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PaginaCadastro from './pages/Cadastro';
import PaginaListaUsuarios from './pages/Lista';
import PaginaAtualizar from './pages/Atualizar';
import PaginaExcluir from './pages/Excluir';
import './App.css'; // Para estilos globais, se necessário

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<PaginaCadastro />} />
        <Route path="/usuarios" element={<PaginaListaUsuarios />} />
        <Route path="/atualizar" element={<PaginaAtualizar />} />
        <Route path="/excluir" element={<PaginaExcluir />} />
      </Routes>
    </Router>
  );
}

export default App;