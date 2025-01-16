import { AppBar, Button, Container, Toolbar } from '@mui/material'
import {ReactNode, useEffect} from 'react'
import { Pathnames } from '../../../router/pathnames'
import { useNavigate } from 'react-router-dom'
import {useTranslation} from "react-i18next";
import i18n from "@/components/layouts/volunteer/i18n";
import LanguageSwitcher from "@/components/layouts/volunteer/components/LanguageSwitcher.tsx";

interface LayoutProps {
    children: ReactNode
}
// i18n.changeLanguage("pl");
// i18n.changeLanguage("en");
// const { t } = useTranslation();
// i18n.t
export const VolunteerLayout = ({ children }: LayoutProps) => {
    const navigate = useNavigate()
    const { t } = useTranslation();
    useEffect(() => {
        const handleLanguageChange = () => {
            const detectedLanguage = navigator.language.split("-")[0]; // np. "en" lub "pl"
            if (i18n.language !== detectedLanguage) {
                i18n.changeLanguage(detectedLanguage);
            }
        };

        // Wywołanie przy pierwszym renderowaniu
        handleLanguageChange();

        // Nasłuchiwanie zmian języka przeglądarki
        window.addEventListener("languagechange", handleLanguageChange);

        return () => {
            window.removeEventListener("languagechange", handleLanguageChange);
        };
    }, [i18n]);

    return (
        <div>
            <AppBar position="static">
                <Toolbar sx={{ display: 'flex'}}>
                    <Button onClick={() => navigate(Pathnames.volunteer.homePage)} sx={{ my: 2, mx: 2, color: 'white' }}>
                        {t("home")}
                    </Button>
                    <Button onClick={() => navigate(Pathnames.volunteer.volunteers)} sx={{ my: 2, mx: 2, color: 'white' }}>
                        {t("volunteerListTitle")}
                    </Button>
                    <Button onClick={() => navigate(Pathnames.volunteer.groups)} sx={{ my: 2, mx: 2, color: 'white' }}>
                        {t("volunteerGroupListTitle")}
                    </Button>
                    <div>
                        <LanguageSwitcher/>
                    </div>
                </Toolbar>
            </AppBar>
            <Container sx={{ p: 2 }}>
                {children}
            </Container>
        </div>
    )
}
