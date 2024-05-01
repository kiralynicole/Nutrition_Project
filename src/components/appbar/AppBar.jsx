import { useMediaQuery, useTheme } from "@mui/material";
import AppBarMobile from "./AppBarMobile";
import AppBarDesktop from "./AppBarDesktop";

export default function AppBar(){

    const theme = useTheme();
    const matches = useMediaQuery(theme.breakpoints.down('md'));

    return (
        <>
        {matches ? <AppBarMobile></AppBarMobile> : <AppBarDesktop></AppBarDesktop>}
        </>
    );
}