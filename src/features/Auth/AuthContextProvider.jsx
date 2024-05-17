import { createContext, useContext, useState } from 'react';

export const AuthContext = createContext(null);

const initialValue = {
  accessToken: null,
  user: null,
};

export function AuthContextProvider({ children }) {
  const [auth, setAuth] = useState(() => {
    const fromStorage = localStorage.getItem('auth');
    if (fromStorage) {
      return JSON.parse(fromStorage);
    }
    return initialValue;
  });

  function login(data) {
    localStorage.setItem('auth', JSON.stringify({
      ...data,
      isAuthenticated: true
    }));
    setAuth({
      user: data,
      isAuthenticated: true
    });
  }

  function logout() {
    localStorage.removeItem('auth');
    setAuth({
      user: initialValue,
      isAuthenticated:false
  });
  }

  return (
    <AuthContext.Provider value={{ ...auth, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuthContext() {
  const ctx = useContext(AuthContext);
  if (ctx === null) {
    throw new Error(
      'Please use "useAuthContext" only inside children of the "AuthContextProvider" component.'
    );
  }
  return ctx;
}
