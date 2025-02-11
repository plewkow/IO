import { AppBar, Button, Container, Toolbar } from '@mui/material'
import { ReactNode } from 'react'
import { Pathnames } from '@/router/pathnames.ts'
import { useNavigate } from 'react-router-dom'
import i18n from "../../../i18n.ts";
import {useTranslation} from "react-i18next";

interface LayoutProps {
    children: ReactNode
}

export const DefaultLayout = ({ children }: LayoutProps) => {
    // TODO tutaj należy dodawać przeciski do paska nawigacji. Jeśli chcemy, aby był dostępny dla któregoś aktora, należy go umieścić w odpowiednim widoku
    const navigate = useNavigate()
    const changeLanguage = (lng: string) => {
        i18n.changeLanguage(lng).catch(console.error);
    };
    const { t } = useTranslation();

    return (
        <div>
            <AppBar position="static">
                <Toolbar sx={{ display: 'flex'}}>
                    <Button onClick={() => navigate(Pathnames.default.homePage)} sx={{ my: 2, mx: 2, color: 'white' }}>
                        Home
                    </Button>
                    <Button onClick={() => navigate(Pathnames.default.loginPage)} sx={{ my: 2, mx: 2, color: 'white' }}>
                        {t("general.login")}
                    </Button>
                    <Button onClick={() => navigate(Pathnames.default.registerPage)} sx={{ my: 2, mx: 2, color: 'white' }}>
                        {t("general.register")}
                    </Button>
                    <Button onClick={() => changeLanguage('en')} sx={{ my: 2, mx: 2, color: 'white' }}>
                        {i18n.t('english')}
                    </Button>
                    <Button onClick={() => changeLanguage('pl')} sx={{ my: 2, mx: 2, color: 'white' }}>
                        {i18n.t('polish')}
                    </Button>
                </Toolbar>
            </AppBar>
            <Container sx={{ p: 2 }}>
                {children}
            </Container>
        </div>
    )
}
