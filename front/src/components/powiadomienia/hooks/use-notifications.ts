

import { useEffect, useState } from "react";
import Notification from "../../../models/powiadomienia/notification";
import api from "@/api/Axios";


/**
 * Hook odpowiedzialny za pobranie listy powiadomień przypisanych do określonego użytkownika.
 *
 * @param userId id użytkownika dla którego pobrane zostaną powiadomienia.
 * @returns zwraca listę zawierający listę powiadomień, funkcję pozwalającą na ustawienie wartości powiadomień oraz zmienną
 *  określającą czy powiadomienia zostały załodowane.
 */
const useNotifications = (userId?: string|null) => {
    const [notifications, setNotifications] = useState<Notification[]>([]);
    const [isLoading,setIsLoading] = useState<boolean>(false) 
    
    
    /**
     * Pobiera powiadomienia dla bieżącego użytkownika z serwera.
     *
     * Metoda konstruuje URI z identyfikatorem użytkownika, wysyła żądanie GET do serwera
     * i aktualizuje stan powiadomień przy użyciu pobranych danych. Obsługuje również stan ładowania.
     *
     * @function
     * @returns {void} Funkcja nie zwraca wartości; aktualizuje stan komponentu.
     *
    */
    const fetchNotifications = async ()=>{
        setIsLoading(true)
        try {
            const response = await api.get("/notifications/user")
            setNotifications(response.data)
            setIsLoading(false)
        }catch(error){
            console.error('Failed to fetch notifications:', error);
        }
    }


    useEffect(()=>{
        if(!userId){
            return
        }
        fetchNotifications()
        const interval = 2 * 1000
        const timerId = setInterval(() => {
            fetchNotifications()
          }, interval);

          return () =>{
            clearInterval(timerId)
          }
    },[userId])



    return [notifications,isLoading] as const
}

export default useNotifications;