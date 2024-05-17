import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup'; 
import { useNavigate } from 'react-router-dom';
import { useAuthContext } from './AuthContextProvider';


const validationSchema = yup.object({
    email: yup.string()
        .required('Please type your email')
        .email('Please type a valid email address'),
    password: yup.string()
        .required('Please type your password')
        .min(6, 'The password needs to be at least 6 characters long'),
});

export function Login(){

    const {user, isAuthenticated, login, logout} = useAuthContext();
    const navigate = useNavigate();

        const { register, handleSubmit, formState: { errors } } = useForm({
            resolver: yupResolver(validationSchema)
        });
    
        function onSubmit(data) {
            console.log(data);

            fetch(`http://localhost:8080/users/login/${data.email}/${data.password}`, {
                method: 'GET'
            }).then(res=>{
                if(!res.ok){
                    throw new Error("email not found");
                }
                return res.json();
            }).then(data=>{
                login(data);
                console.log(data);
            }).catch(()=>{
                alert("No accounts match the provided email and password.");
            });
            
        }

        if (isAuthenticated){
            return(
                <>
                <h1>Hello, {user?.name}</h1>
                <p>Welcome back! Check out our latest protein products that can help you reach your fitness goals faster. Don't miss our exclusive deals just for you.</p>
                    <button onClick={() => navigate('/')}>Shop Now</button>
                    <button onClick = {logout}>Logout</button>
                </>
            );
            
        }

        
        

    return (

        
        <form className="brandForm" onSubmit={handleSubmit(onSubmit)}>
        <h1 className="fullWidth">Login</h1>
        <label htmlFor="email">Email</label>
          <input type="email" id="email" {...register('email')} />
          {errors.email && <p className="errorMessage">{errors.email.message}</p>}
    
          <label htmlFor="password">Password</label>
          <input type="password" id="password" {...register('password')} />
          {errors.password && <p className="errorMessage">{errors.password.message}</p>}

  
        <button type="submit" className="btn secondColumn">Login
        </button>
      </form>
        
    );
}