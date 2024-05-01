import { styled } from "@mui/material";
import {Box} from '@mui/system';
import "@fontsource/montez"

export const AppBarContainer = styled(Box)(()=>({
    display: 'flex',
    marginTop: 4,
    justifyContent:'center',
    alignItems:'center',
    padding:'2px 8px'

}))

export const AppBarHeader = styled(Typography)(()=>({
    padding:'4px',
    flexGrow: 1,
    fontSize: '4em',
    fontFamily: '"Montez", "cursive"',
    color: Colors.secondary,
}))