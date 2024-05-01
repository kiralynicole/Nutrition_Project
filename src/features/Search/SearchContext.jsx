import { createContext, useState } from "react";

const SearchContext = createContext();

export const useSearch = () => useContext(SearchContext);

export const SearchProvider = ({children}) => {
    const [searchTerm, setSearchTerm] = useState('');

    return (
        <SearchContext.Provider value = {{searchTerm, setSearchTerm}}>

    )
}

